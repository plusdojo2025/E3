package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChatDao;
import dto.Chat;
import dto.Taxi;;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/LoginServlet");
//			return;
//		}
		
		// テスト用　タクシー会社情報作成
		List<Taxi> taxiList = new ArrayList<Taxi>();
		for (int i=0; i<3; i++) {
			Taxi taxi = new Taxi();
			taxi.setCompany("タクシー会社" + (i+1));
			taxi.setPhone("03-123-4567");
			taxiList.add(taxi);
		}
		request.setAttribute("taxiList", taxiList);
		
		// 近くのタクシー会社情報取得
//		TaxiDao taxiDao = 
		
		// apointment.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/appointment.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		
		// 申請情報id, 送信者id, チャットメッセージ, 送信日時を取得
		request.setCharacterEncoding("UTF-8");
		int roomId = Integer.parseInt(request.getParameter("room_id"));
		int senderId = (int) session.getAttribute("id");
		String chatText = request.getParameter("selectPhrase");
		Date nowDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String chatDate = sdf1.format(nowDate);
		
		// チャット登録処理
		ChatDao chatDao = new ChatDao();
		Chat chat = new Chat(0, roomId, senderId, chatText, chatDate);
		// 実行結果判定
		if (chatDao.insertChat(chat)) { 		// 登録が成功した場合
			// テスト用
			System.out.println("登録成功");
			
			// appointment.jspにリダイレクト　再読み込みによる同一データ登録防止
			// ゆくゆくはチャット部分のみリロードするように
			response.sendRedirect(request.getContextPath() + "/AppointmentServlet");
		} else { 								// 登録失敗
			System.out.println("登録失敗");
		}
		
	}

}
