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
import dao.TaxiDao;
import dto.RequestJoin;
import dto.Taxi;;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		
		// ログインユーザーの直近の予約情報取得処理
		RequestDao requestDao = new RequestDao();
		int requestId = requestDao.getRequestId((int)session.getAttribute("id"));
		
		// リクエスト情報取得処理
		RequestJoin reqj = requestDao.getRequestInfo((int)session.getAttribute("id"), requestId);
		
		// 近くのタクシー会社情報取得
		TaxiDao taxiDao = new TaxiDao();
		List<Taxi> taxiList = taxiDao.searchTaxiCompany((int)session.getAttribute("id"), reqj);
		
		// リクエストスコープに格納
		request.setAttribute("reqj", reqj);
		request.setAttribute("taxiList", taxiList);
		request.setAttribute("roomId", requestId);		// リクエストidをチャットのルームidとして使用
		
		// apointment.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/appointment.jsp");
		dispatcher.forward(request, response);
	}

}
