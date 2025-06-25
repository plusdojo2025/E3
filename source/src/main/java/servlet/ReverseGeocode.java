package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets; // 追加

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/ReverseGeocode")
public class ReverseGeocode extends HttpServlet {    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 緯度, 経度を取得
		request.setCharacterEncoding("UTF-8");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		
		String apiUrl = "https://nominatim.openstreetmap.org/reverse?lat=" + lat
				+ "&lon=" + lng + "&format=json&accept-language=ja";
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // レスポンスの文字コードをUTF-8に設定
        StringBuilder jsonResponse = new StringBuilder();
		
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Nominatimの利用規約に従い、User-Agentヘッダーを設定することを強く推奨します。
            // ここでは仮の名前を設定していますが、あなたのアプリケーションを識別できる名前に変更してください。
            conn.setRequestProperty("User-Agent", "SheraTaxi/1.0 (mizuno-koume-plusdojo2025@seplus2016.onmicrosoft.com)");
            
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
            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
            // 住所情報を抽出
            JSONObject address = jsonObject.getJSONObject("address");

            // JSONで返却
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(address.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\":\"サーバー側で予期せぬエラーが発生しました。\"}");
        }
    }
}