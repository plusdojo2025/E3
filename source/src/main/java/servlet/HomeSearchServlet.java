package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StandByUserDao;
import dto.StandByUser;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ホーム＆検索画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home_search.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt("id");
		String current_latitude = request.getParameter("current_latitude");
		String current_longitude = request.getParameter("current_longitude");
		String drop_off_latitude = request.getParameter("drop_off_latitude");
		String drop_off_longitude = request.getParameter("drop_off_longitude");
		int headcount = Integer.parseInt(request.getParameter("headcount"));

		// 登録処理を行う
		StandByUserDao = new StandByUserDao();
		List<StandByUser> userList = searchByCondition(date, current_latitude, current_longitude,
				drop_off_latitude, drop_off_longitude, headcount);

		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	
}
}
