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
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		System.out.println(session.getAttribute("id"));
		UserDao uDao = new UserDao();
		User user = new User();
		user = uDao.searchUser(id);
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("name", user.getName());
		request.setAttribute("gender", user.getGender());
		request.setAttribute("nickname", user.getNickname());
		request.setAttribute("address", user.getAddress());
		request.setAttribute("noSmoking", user.getSmoking());
		request.setAttribute("noTalking", user.getTalking());
		request.setAttribute("sameGender", user.getPartner_gender());
		
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
		int gender = Integer.parseInt(request.getParameter("gender"));
		double latitude = Double.parseDouble(request.getParameter("address_latitude"));
		double longitude = Double.parseDouble(request.getParameter("address_longitude"));	
		int talking = Integer.parseInt(request.getParameter("noTalking"));
		int smoking = Integer.parseInt(request.getParameter("noSmoking"));
		int partner_gender = Integer.parseInt(request.getParameter("sameGender"));
		String address = request.getParameter("address");
		
		UserDao uDao =  new UserDao();
		
		if(uDao.updateUser(new User( 
				name,  nickname,  gender,  
				latitude,  longitude,  talking,  
				smoking,  partner_gender,  id,  
				address), id)){// 更新成功
			response.sendRedirect(request.getContextPath() + "/HomeSearchServlet");
		}else{ // 登録失敗
			response.sendRedirect(request.getContextPath() + "/EditUserServlet");
		}
	}
}
