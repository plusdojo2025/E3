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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// user.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		//デバッグ用
		System.out.println(session.getAttribute("id"));
		
		//セッションスコープに格納したidの値を取得
		int id = (int)session.getAttribute("id");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		int gender = Integer.parseInt(request.getParameter("gender"));
		double latitude = Double.parseDouble(request.getParameter("address_latitude"));
		double longitude = Double.parseDouble(request.getParameter("address_longitude"));
		int talking = Integer.parseInt(request.getParameter("noTalking"));
		int smoking = Integer.parseInt(request.getParameter("noSmoking"));
		int partner_gender = Integer.parseInt(request.getParameter("sameGender"));
		String address = request.getParameter("address");
		
		UserDao idpw =  new UserDao();
		
		if(idpw.insertUser(new User( 
				name,  nickname,  gender,  
				latitude,  longitude,  talking,  
				smoking,  partner_gender,  id,  
				address))){// 登録成功
			response.sendRedirect("/E3/HomeSearchServlet");
		}else{ // 登録失敗
			response.sendRedirect("/E3/UserServlet");
		}
	}
}
