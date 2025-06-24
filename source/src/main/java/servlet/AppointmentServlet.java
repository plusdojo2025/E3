package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/LoginServlet");
//			return;
//		}
		
		// テスト用　タクシー会社情報作成
		List<Taxi> taxiList = new ArrayList<Taxi>();
		for (int i=0; i<3; i++) {
			Taxi taxi = new Taxi();
			taxi.setCompany("タクシー会社" + (i+1));
			taxi.setPhone("03-123-4567");
			taxiList.add(taxi);
		}
		request.setAttribute("taxiList", taxiList);
		// テスト用　ルームid
		int roomId = -1;
		request.setAttribute("roomId", roomId);
		// テスト用　ログインユーザーid
		int id = -11;
		session.setAttribute("id", id);
				
		// 近くのタクシー会社情報取得
//		TaxiDao taxiDao = 
		
		// apointment.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/appointment.jsp");
		dispatcher.forward(request, response);
	}

}
