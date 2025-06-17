package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.User;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// edit_user.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit_user.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		/*
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/E3/LoginServlet");
			return;
		}
		*/
		
		request.setCharacterEncoding("UTF-8");
		//デバッグ用
		System.out.println(session.getAttribute("id"));
		
		//セッションスコープに格納したidの値を取得
		int id = (int)session.getAttribute("id");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		int gender = 0;
		double latitude = 0.0;
		double longitude = 0.0;
		int talking = 0;
		int smoking = 0;
		int partner_gender = 0;
		String address = request.getParameter("address");
		
		UserDao idpw =  new UserDao();
		
		if(idpw.insertUser(new User( 
				name,  nickname,  gender,  
				latitude,  longitude,  talking,  
				smoking,  partner_gender,  id,  
				address))){// 更新成功
			response.sendRedirect("/E3/HomeSearchServlet");
		}else{ // 登録失敗
			response.sendRedirect("/E3/EditUserServlet");
		}
	}
}
