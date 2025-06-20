package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder; // 追加
import java.nio.charset.StandardCharsets; // 追加
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/Geocode")
public class Geocode extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        System.out.println(2);
        System.out.println("address param: [" + address + "]");
        System.out.println(1);
        
        // 住所をURLエンコード
        String encodedAddress = "";
        try {
            encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"住所のエンコード中にエラーが発生しました。\"}");
            return; // エラーが発生した場合はここで処理を終了
        }

        String apiUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedAddress;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // レスポンスの文字コードをUTF-8に設定
        StringBuilder jsonResponse = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Nominatimの利用規約に従い、User-Agentヘッダーを設定することを強く推奨します。
            // ここでは仮の名前を設定していますが、あなたのアプリケーションを識別できる名前に変更してください。
            conn.setRequestProperty("User-Agent", "SheraTaxi/1.0 (kurose-taichi-plusdojo2025@seplus2016.onmicrosoft.com)");
            System.out.println(conn.getRequestProperty("User-Agent"));
            
            int responseCode = conn.getResponseCode(); // HTTPレスポンスコードを取得
            System.out.println(responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) { // ステータスコードが200(OK)でない場合
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                errorReader.close();
                System.err.println("Nominatim APIからのエラーレスポンス: " + errorResponse.toString());
                response.getWriter().write("{\"error\":\"Nominatim APIからエラーが返されました。ステータスコード: " + responseCode + "\"}");
                return;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)); // レスポンスもUTF-8で読み込み
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                jsonResponse.append(inputLine);
            }
            in.close();

            // JSONレスポンスの解析
            JSONArray jsonArray = new JSONArray(jsonResponse.toString());
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                double latitude = jsonObject.getDouble("lat");
                double longitude = jsonObject.getDouble("lon");

                // JSON形式で返す準備（ObjectMapper使用）
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("latitude", latitude);
                resultMap.put("longitude", longitude);

                // JSON形式に変換して送信
                String jsonStr = mapper.writeValueAsString(resultMap);
                response.getWriter().write(jsonStr);
            }else {
                response.getWriter().write("{\"error\":\"指定された住所の緯度経度が見つかりませんでした。\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\":\"サーバー側で予期せぬエラーが発生しました。\"}");
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // GET処理をそのまま使う（パラメータの取得方法は同じなので）
    }
}