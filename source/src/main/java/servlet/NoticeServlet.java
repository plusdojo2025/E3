package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RequestDao;
import dao.UserDao;
import dto.RequestJoin;
import dto.User;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		// 検索処理を行う
		RequestDao reqDao = new RequestDao();
		List<RequestJoin> reqList = reqDao.searchRequestMe((int)session.getAttribute("id"));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("reqList", reqList);
		
		UserDao userDao = new UserDao();
		User user = userDao.searchUser((int)session.getAttribute("id"));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("user", user);

		// 通知ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int partner_id = Integer.parseInt(request.getParameter("partner_id"));

		// 更新または削除を行う
		RequestDao reqDao = new RequestDao();
		if (request.getParameter("submit").equals("承認")) {
			if(reqDao.updateRequest(partner_id, 1)) {	//成功
				// 通知ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp");
				dispatcher.forward(request, response);
			}
		}else if(request.getParameter("submit").equals("却下")){
			if(reqDao.updateRequest(partner_id, 2)) {	//成功
				// 通知ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp");
				dispatcher.forward(request, response);
			}
		}else {
			//予約確認サーブレットにリダイレクトする
			response.sendRedirect("/webapp/AppointmentServlet");
		}
	}
}
