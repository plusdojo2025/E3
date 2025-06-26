package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.RequestDao;
import dto.RequestJoin;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();

        //処理（DB呼び出し等）
        // リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		try {

	        //パラメータ取得
	        int param1 = Integer.parseInt(String.valueOf(session.getAttribute("id")));


			// 検索処理を行う
			RequestDao reqDao = new RequestDao();
			List<RequestJoin> reqList = reqDao.searchRequestMe(param1);
			
			System.out.println(reqList);
			
			List<RequestJoin> sendAnsList = reqDao.sendForNotice(param1);
			
			System.out.println(sendAnsList);
			
			List<RequestJoin> respAnsList = reqDao.respForNotice(param1);
			
			System.out.println(respAnsList);

	        //出力(レスポンスをmapに格納してJSON化)
	        //ヘッダ設定
	        response.setContentType("application/json;charset=UTF-8");   //JSON形式, UTF-8

	        //JSONマップ
	        //HashMap格納
	        /* 
	         * request[
	         * {
	         * 		"response1" : "response1の値"
	         * },
	         * {
	         *		・・・
	         * }
	         * ]
	         * */
	        //マッパ(JSON <-> Map, List)
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> mapMsg = new HashMap<>();
	        mapMsg.put("reqList", reqList);
	        mapMsg.put("sendAnsList", sendAnsList);
	        mapMsg.put("respAnsList", respAnsList);
	        //JSON形式に変換
	        String jsonStr = mapper.writeValueAsString(mapMsg);

	        //JSON形式として文字列を送信
	        response.getWriter().write(jsonStr);

	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}
