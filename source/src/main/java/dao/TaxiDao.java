package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RequestJoin;
import dto.Taxi;

public class TaxiDao {

	// 申請者の現在地と近いタクシー会社情報取得
	public List<Taxi> searchTaxiCompany(int loginUserId, RequestJoin reqj) {
		Connection conn = null;
		List<Taxi> taxiList = new ArrayList<Taxi>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select company, phone,(6371 * acos(cos(radians(?)) * cos(radians(taxi_address_latitude)) * cos(radians(taxi_address_longitude) - radians(?)) + sin(radians(?)) * sin(radians(taxi_address_latitude)))) AS distance FROM taxi ORDER BY distance LIMIT 3";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// ログインユーザーが待機者か申請者か
			if (reqj.getId() == loginUserId) {		// ログインユーザーが申請者の場合
				// 申請者（ログインユーザー）の現在地と近い会社を検索
				pStmt.setDouble(1, reqj.getCurrent_latitude());
				pStmt.setDouble(2, reqj.getCurrent_longitude());
				pStmt.setDouble(3, reqj.getCurrent_latitude());
			} else {								// ログインユーザーが待機者の場合
				// 申請者（相手）の現在地と近い会社を検索
				pStmt.setDouble(1, reqj.getPrtnr_current_latitude());
				pStmt.setDouble(2, reqj.getPrtnr_current_longitude());
				pStmt.setDouble(3, reqj.getPrtnr_current_latitude());
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Taxi taxi = new Taxi();
				taxi.setCompany(rs.getString("company"));
				taxi.setPhone(rs.getString("phone"));

				taxiList.add(taxi);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}
		return taxiList;
	}
}
