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
import dao.StandByUserDao;
import dto.Request;
import dto.StandByUser;

/**
 * Servlet implementation class SearchResultServlet
 */
@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// ログインしていない場合ログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/E3/LoginServlet");
//			return;
//		}
		
		// 検索結果一覧ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインしていない場合ログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/E3/LoginServlet");
//			return;
//		}
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		//StandByUser用
		int id = (int)session.getAttribute("id");
		String dDate = request.getParameter("desired_date"); 
		double cLat = Double.parseDouble(request.getParameter("current_latitude"));
		double cLon = Double.parseDouble(request.getParameter("current_longitude")); 
		double dLat = Double.parseDouble(request.getParameter("drop_off_latitude")); 
		double dLon = Double.parseDouble(request.getParameter("drop_off_longitude")); 
		int headcount = Integer.parseInt(request.getParameter("desired_date")); 
		String rDate = request.getParameter("registration_date"); 
		int talking = Integer.parseInt(request.getParameter("talking")); 
		int smoking = Integer.parseInt(request.getParameter("smoking")); 
		int prtnrGen = Integer.parseInt(request.getParameter("partner_gender")); 
		//Request用
		int prtnrId = Integer.parseInt(request.getParameter("partner_id")); 
		int sbId = Integer.parseInt(request.getParameter("stand_by_id")); 
		
		if(request.getParameter("reqSta").equals("待機登録")) {
			StandByUserDao sDao = new StandByUserDao();
			StandByUser SBUser = new StandByUser(0, id, dDate, cLat, cLon, dLat, dLon, headcount, 1, rDate, talking, smoking, prtnrGen);
			sDao.insertStandByUser(SBUser);
			response.sendRedirect("/E3/HomeSearchServlet.java");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home_search.jsp");
//			dispatcher.forward(request, response);
		}
		else if(request.getParameter("reqSta").equals("申請")) {
			RequestDao rDao = new RequestDao();
			Request req = new Request(0, id, cLat, cLon, dLat, dLon, headcount, 0, prtnrId, talking, smoking, prtnrGen, rDate, sbId);
			rDao.insertRequest(req);
		}
		

	}

}
