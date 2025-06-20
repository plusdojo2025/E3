package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; // Session

import dao.IdPwDao;
import dto.IdPw;
import dto.ResultUserID;

/**
 * Servlet implementation class IdPwServlet
 */
@WebServlet("/IdPwServlet")
public class IdPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// idpw.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/idpw.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// リクエストパラメータを取得する
		
		//セッションの開始
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		String email = request.getParameter("mailaddress");
		String pass = request.getParameter("loginPW");
		
		IdPwDao idpw =  new IdPwDao();
		ResultUserID result = idpw.insertIdpw(new IdPw(id, email, pass));
		
		if(result.isResult()){ // 登録成功		
			//セッションスコープにidを格納
			session.setAttribute("id", result.getId());
			
			//デバッグ用
			System.out.println(session.getAttribute("id"));
			response.sendRedirect(request.getContextPath() + "/UserServlet");
		}else{ // 登録失敗
			response.sendRedirect(request.getContextPath() + "/IdPwServlet");
		}
	}
}
