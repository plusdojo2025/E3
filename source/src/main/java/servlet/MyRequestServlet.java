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

import dao.StandByUserDao;
import dto.StandByUser;

/**
 * Servlet implementation class MyRequestServlet
 */
@WebServlet("/MyRequestServlet")
public class MyRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/LoginServlet");
//			return;
//		}
		
//		// ログインユーザーのid取得
//		request.setCharacterEncoding("UTF-8");
//		int id = (int) session.getAttribute("id");
		
//		// 自分の待機情報取得処理
//		StandByUserDao standByUserDao = new StandByUserDao();
//		List<StandByUser> standByInfoList = standByUserDao.getAllStandByInfo(id);
//		// 待機情報を格納
//		request.setAttribute("standByInfoList", standByInfoList);
		
		// テスト用　待機情報作成
		List<StandByUser> standByInfoList = new ArrayList<StandByUser>();
		StandByUser standByUser1 = new StandByUser();
		standByUser1.setRegistration_date("2025/06/09 23:00");
		standByUser1.setHeadcount(1);
		standByUser1.setDate("2025/06/09 23:20");
		standByUser1.setStand_by_id(-1);
		standByInfoList.add(standByUser1);
		StandByUser standByUser2 = new StandByUser();
		standByUser2.setRegistration_date("2025/06/13 24:10");
		standByUser2.setHeadcount(1);
		standByUser2.setDate("2025/06/13 24:30");
		standByUser2.setStand_by_id(-2);
		standByInfoList.add(standByUser2);
		StandByUser standByUser3 = new StandByUser();
		standByUser3.setRegistration_date("2025/06/15 11:00");
		standByUser3.setHeadcount(2);
		standByUser3.setDate("2025/06/15 13:00");
		standByUser3.setStand_by_id(-3);
		standByInfoList.add(standByUser3);
		request.setAttribute("standByInfoList", standByInfoList);
		
		// my_request.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_request.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン状態チェック
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/LoginServlet");
//			return;
//		}
		
		// 待機情報idを取得
		request.setCharacterEncoding("UTF-8");
		int standById = Integer.parseInt(request.getParameter("stand_by_id"));
		
		// テスト用　待機情報id取得確認
		System.out.println(standById);
		
		// 待機状態解除処理
		StandByUserDao standByUserDao = new StandByUserDao();
		// 実行結果判定
		if (standByUserDao.deleteStandByInfo(standById)) { 		// 削除が成功した場合
			// テスト用
			System.out.println("削除成功");
			
			// my_request.jspにリダイレクト　再読み込みによる同一データ登録防止
			response.sendRedirect(request.getContextPath() + "/MyRequestServlet");
		} else { 								// 削除失敗
			// テスト用
			System.out.println("削除失敗");
			response.sendRedirect(request.getContextPath() + "/MyRequestServlet");
		}
		
	}

}
