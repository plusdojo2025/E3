package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;
import dto.Chat;

/**
 * Servlet implementation class SendChatServlet
 */
@WebServlet("/SendChatServlet")
public class SendChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/LoginServlet");
//			return;
//		}
		
		// テスト用　送信者id
		int senderId = -11;
		
		// 申請情報id, 送信者id, チャットメッセージ, 送信日時を取得
		request.setCharacterEncoding("UTF-8");
		int roomId = Integer.parseInt(request.getParameter("room_id"));
//		int senderId = (int) session.getAttribute("id");
		String chatText = request.getParameter("selectPhrase");
		String chatDate = request.getParameter("chat_date"); 
		
		System.out.println(roomId);
		System.out.println(senderId);
		System.out.println(chatText);
		System.out.println(chatDate);
		
		// チャット登録処理
		ChatDao chatDao = new ChatDao();
		Chat chat = new Chat(0, roomId, senderId, chatText, chatDate);
		// 実行結果判定
		if (chatDao.insertChat(chat)) { 		// 登録が成功した場合
			// テスト用
			System.out.println("登録成功");
		} else { 								// 登録失敗
			// テスト用
			System.out.println("登録失敗");
		}
	}

}
