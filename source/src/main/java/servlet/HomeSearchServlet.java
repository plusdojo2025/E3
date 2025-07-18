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

import dao.StandByUserDao;
import dto.StandByUser;
import dto.StandByUserJoin;

/**
 * Servlet implementation class HomeSearchServlet
 */
@WebServlet("/HomeSearchServlet")
public class HomeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ホーム＆検索画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home_search.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		//StandByUser用
		int id = (int)session.getAttribute("id");
		String dDate = request.getParameter("date");
		dDate = dDate.replace("T", " "); // T をスペースに変換
		System.out.println("date" + dDate);
		double cLat = Double.parseDouble(request.getParameter("current_latitude"));
		double cLon = Double.parseDouble(request.getParameter("current_longitude")); 
		double dLat = Double.parseDouble(request.getParameter("drop_off_latitude")); 
		double dLon = Double.parseDouble(request.getParameter("drop_off_longitude")); 
		int headcount = Integer.parseInt(request.getParameter("headcount")); 
		int talking = 1;
		int smoking = 1;
		int partner_gender = 1;

		request.setAttribute("cLat", cLat);
		request.setAttribute("cLon", cLon);
		request.setAttribute("dLat", dLat);
		request.setAttribute("dLon", dLon);
		request.setAttribute("headcount", headcount);
		request.setAttribute("talking", talking);
		request.setAttribute("smoking", smoking);
		request.setAttribute("partner_gender", partner_gender);

		String submitVal = request.getParameter("search");
		if("検索".equals(submitVal)) {
			StandByUserDao sDao = new StandByUserDao();
			StandByUser sbu = new StandByUser(0, id, dDate, cLat, cLon, dLat, dLon, headcount, 1, "", 0, 0, 0);
			
			sDao.insertStandByUser(sbu);
			
			List<StandByUserJoin> sbujLi = sDao.searchStandByInfo(id);
			request.setAttribute("StandByUserJoin", sbujLi);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
			dispatcher.forward(request, response);
		}
	}
}


