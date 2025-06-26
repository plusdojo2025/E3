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
import dao.StandByUserDao;
import dao.UserDao;
import dto.Request;
import dto.StandByUser;
import dto.StandByUserJoin;
import dto.User;

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
		UserDao uDao = new UserDao();
		User user = uDao.searchUser(id);
		String dDate = request.getParameter("date"); 
		double cLat = Double.parseDouble(request.getParameter("current_latitude"));
		double cLon = Double.parseDouble(request.getParameter("current_longitude")); 
		double dLat = Double.parseDouble(request.getParameter("drop_off_latitude")); 
		double dLon = Double.parseDouble(request.getParameter("drop_off_longitude")); 
		int headcount = Integer.parseInt(request.getParameter("headcount")); 
		String rDate = request.getParameter("registration_date"); 
		int talking = user.getTalking();
		if(request.getParameter("talking") != null) {
			talking = Integer.parseInt(request.getParameter("talking")); 
		}
		int smoking = user.getSmoking();
		if(request.getParameter("smoking") != null) {
			smoking = Integer.parseInt(request.getParameter("smoking")); 
		}
		int prtnrGen = user.getPartner_gender();
		if(request.getParameter("partner_gender") != null) {
			prtnrGen = Integer.parseInt(request.getParameter("partner_gender")); 
		}
		
		if(request.getParameter("search") != null) {
			if(request.getParameter("search").equals("検索")) {
				StandByUserDao sDao = new StandByUserDao();
				StandByUser sbu = new StandByUser(0, id, dDate, cLat, cLon, dLat, dLon, headcount, 1, rDate, talking, smoking, prtnrGen);
				
				boolean rr = sDao.insertStandByUser(sbu);
				request.setAttribute("cLat", cLat);
				request.setAttribute("cLon", cLon);
				request.setAttribute("dLat", dLat);
				request.setAttribute("dLon", dLon);
				System.out.println(rr);
				List<StandByUserJoin> sbujLi = sDao.searchStandByInfo(id);
				request.setAttribute("StandByUserJoin", sbujLi);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
				dispatcher.forward(request, response);
			}
		}
		if(request.getParameter("stand") != null) {
			if(request.getParameter("stand").equals("待機登録")) {
				StandByUserDao sDao = new StandByUserDao();
				sDao.updateFlag(id);
				response.sendRedirect(request.getContextPath() + "/HomeSearchServlet");
			}
		}
		if(request.getParameter("Request") != null) {
			if(request.getParameter("Request").equals("申請")) {
				int prtnrId = Integer.parseInt(request.getParameter("partner_id"));
				int sbId = Integer.parseInt(request.getParameter("stand_by_id")); 
				StandByUserDao sbud = new StandByUserDao();
				String date = sbud.getDate(sbId);
				RequestDao rDao = new RequestDao();
				Request req = new Request(0, id, cLat, cLon, dLat, dLon, headcount, 0, prtnrId, talking, smoking, prtnrGen, rDate, sbId, date);
				rDao.insertRequest(req);
			}
		}
	}
}
