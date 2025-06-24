package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IdPwDao;
import dto.IdPw;
import dto.ResultUserID;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// login.jspにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			// int id = Integer.parseInt("id");
			String pass = request.getParameter("mailaddress");
			String email = request.getParameter("loginPW");
			
			// ログイン処理を行う
			IdPwDao iDao = new IdPwDao();
			ResultUserID resultId = iDao.loginCheck(new IdPw(0, pass, email));
			if (resultId.isResult()) {
				// セッションスコープにIDを格納する
				HttpSession session = request.getSession();
				session.setAttribute("id", resultId.getId());
				// メニューサーブレットにリダイレクトする
				response.sendRedirect(request.getContextPath() + "/HomeSearchServlet");
			}  else { // ログイン失敗
				response.sendRedirect(request.getContextPath() + "/LoginServlet");
			} 

	}
}
