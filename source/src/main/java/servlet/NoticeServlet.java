package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RequestDao;

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
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/E3/LoginServlet");
//			return;
//		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

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
		int partner_id = Integer.parseInt(request.getParameter("id"));

		// 承認または却下を行う
		RequestDao reqDao = new RequestDao();
		if (request.getParameter("submit").equals("承認")) {				//submitが"承認"
			if(reqDao.updateRequest(partner_id, 1)) {	//成功
				// 通知ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp");
				dispatcher.forward(request, response);
			}
		}else if(request.getParameter("submit").equals("却下")){			//submitが"却下"
			if(reqDao.updateRequest(partner_id, 2)) {	//成功
				// 通知ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp");
				dispatcher.forward(request, response);
			}
		}else {															//submitが""
			//何かに使うかも？
		}
	}
}
