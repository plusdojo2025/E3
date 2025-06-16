package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StandByUser;

public class StandByUserDao {
	// 必要事項登録　検索時
	public boolean insertStandByUser(StandByUser standByUser) {
		Connection conn = null;
		boolean insertResult = false;			// trueの場合は成功, falseの場合は失敗
		
		try {
			// JDstandByUserドライバを読み込む
			Class.forName("com.mysql.cj.jdstandByUser.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdstandByUser:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			String sql = "insert into StandByUser values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, standByUser.getStand_by_id());
			pStmt.setInt(2, standByUser.getId());
			pStmt.setString(3, standByUser.getDate());
			pStmt.setDouble(4, standByUser.getCurrent_latitude());
			pStmt.setDouble(5, standByUser.getCurrent_longitude());
			pStmt.setDouble(6, standByUser.getDrop_off_latitude());
			pStmt.setDouble(7, standByUser.getDrop_off_longitude());
			pStmt.setInt(8, standByUser.getHeadcount());
			pStmt.setInt(9, 0);										// 検索時はデフォルトで0(非待機状態)
			pStmt.setString(10, standByUser.getRegistration_date());
			pStmt.setInt(11, standByUser.getTalking());
			pStmt.setInt(12, standByUser.getSmoking());
			pStmt.setInt(13, standByUser.getPartner_gender());

			// SQL文を実行して更新行数を取得　1行の場合は成功
			if (pStmt.executeUpdate() == 1) {
				insertResult = true;
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

		// 結果を返す
		return insertResult;
	}
	
	// 自分の待機情報一覧取得　待機情報確認画面に表示
	public List<StandByUser> getAllStandByInfo(int id) {
		Connection conn = null;
		// 取得データを格納するリスト
		List<StandByUser> standByInfoList = new ArrayList<StandByUser>();		

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			String sql = "select headcount, current_latitude, current_longitude,"
					+ "drop_off_latitude, drop_off_longitude,"
					+ "registration_date, date from StandByUser "
					+ "where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SQL文を実行して検索結果を取得
			ResultSet rs = pStmt.executeQuery();

			// 検索結果をコレクションに格納
			while (rs.next()) {
				StandByUser standByUser = new StandByUser();
				standByUser.setHeadcount(rs.getInt("headcount"));
				standByUser.setCurrent_latitude(rs.getDouble("current_latitude"));
				standByUser.setCurrent_longitude(rs.getDouble("current_longitude"));
				standByUser.setDrop_off_latitude(rs.getDouble("drop_off_latitude"));
				standByUser.setDrop_off_longitude(rs.getDouble("drop_off_longitude"));
				standByUser.setRegistration_date(rs.getString("registration_date"));
				standByUser.setDate(rs.getString("date"));
				standByInfoList.add(standByUser);
			}

			// 検索結果が格納されたコレクションを返す
			return standByInfoList;
		}
		catch (Exception e) {
			// 例外処理
			e.printStackTrace();
			return null;
		}
		finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 待機情報検索処理
	
	
	// 待機情報flag更新
	public boolean updateFlag(int flag, int stand_by_id) {
		Connection conn = null;
		boolean updateResult = false;			// trueの場合は成功, falseの場合は失敗
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			String sql = "update StandByUser set flag = ? "
					+ "where stand_by_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, flag);
			pStmt.setInt(2, stand_by_id);

			// SQL文を実行して更新行数を取得　1行の場合は成功
			if (pStmt.executeUpdate() == 1) {
				updateResult = true;
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

		// 結果を返す
		return updateResult;
	}
	
	// 予定日を超過したデータ削除
	public boolean deleteStandByInfo() {
		Connection conn = null;
		int deleteCount = 0;					// 予定日を超過したデータ数 
		boolean deleteResult = false;			// trueの場合は成功, falseの場合は失敗
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			// 予定日を超過したデータ数を求めるSQL
			String countSql = "select count(*) from StandByUser where date < curdate();";
			PreparedStatement cPStmt = conn.prepareStatement(countSql);
			// 削除するSQL
			String deleteSql = "delete from StandByUser where date < curdate();";
			PreparedStatement dPStmt = conn.prepareStatement(deleteSql);

			// データ数を求めるSQL文を実行してデータ数を格納
			ResultSet rs = cPStmt.executeQuery();
			rs.next();
			deleteCount = rs.getInt("count(*)");
			
			// 削除するSQL文を実行して更新行数を取得
			// 予定日を超過したデータ数と等しい場合は成功
			if (dPStmt.executeUpdate() == deleteCount) {
				deleteResult = true;
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

		// 結果を返す
		return deleteResult;
	}	
	
	// 自分の待機状態解除処理
	public boolean deleteStandByInfo(int id) {
		Connection conn = null;
		boolean deleteResult = false;			// trueの場合は成功, falseの場合は失敗
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			String sql = "delete from StandByUser where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SQL文を実行して更新行数を取得　1行の場合は成功
			if (pStmt.executeUpdate() == 1) {
				deleteResult = true;
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

		// 結果を返す
		return deleteResult;
	}
}
