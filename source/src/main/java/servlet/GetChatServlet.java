package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ChatDao;
import dto.Chat;

/**
 * Servlet implementation class GetChatServlet
 */
@WebServlet("/GetChatServlet")
public class GetChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		
		// jsからルームidを取得
		request.setCharacterEncoding("UTF-8");
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		
		// チャットログ取得処理
		ChatDao chatDao = new ChatDao();
		List<Chat> chatList = chatDao.displayChat(roomId);
		
		// 取得したチャットログのリストをjson形式に変換
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(chatList);
		
		// jsにチャットログを送る
		response.setContentType("application/json; charset=UTF-8");
	    response.getWriter().write(json);		
	}

}
