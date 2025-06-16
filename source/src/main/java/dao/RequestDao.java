package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Request;
import dto.RequestJoin;
import dto.User;

public class RequestDao {
	
	public boolean insertRequest(Request req) {
		Connection conn = null;
		boolean insertResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// INSERT文を準備する
			String sql = "insert into Request values( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, );
			pStmt.setDouble(2, req.getCurrent_latitude());
			pStmt.setDouble(3, req.getCurrent_longitude());
			pStmt.setDouble(4, req.getDrop_off_latitude());
			pStmt.setDouble(5, req.getDrop_off_longitude());
			pStmt.setInt(6, req.getHeadcount());
			pStmt.setInt(7, req.getStatus());
			pStmt.setInt(8, req.getPartner_id());
			pStmt.setInt(9, req.getTalking());
			pStmt.setInt(10, req.getSmoking());
			pStmt.setInt(11, req.getPartner_gender());
			pStmt.setString(12, req.getRegistration_date());
			pStmt.setInt(13, req.getStand_by_id());
							
			if (pStmt.executeUpdate() == 1) {
				insertResult = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			insertResult = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			insertResult = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					insertResult = false;
				}
			}
		}
		// 結果を返す
		return insertResult;
	}
	
	public boolean updateRequest(Request req) {
		Connection conn = null;
		boolean updateResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "update Request set status = ? where request_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, req.getStatus());
			pStmt.setInt(2, req.getRequest_id());
			
			// SQL文を実行する
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
	
	
	public List<RequestJoin> searchRequestMe(RequestJoin reqj) {
		Connection conn = null;
		List<RequestJoin> reqjList = new ArrayList<RequestJoin>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select nickname, gender, headcount, current_latitude, current_longitude, drop_off_latitude, drop_off_longitude, registration_date  from Request join User on Request.id = User.id where partner_id = ? and status = 0;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, 7);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				reqj.setNickname(rs.getString("nickname"));
				reqj.setGender(rs.getInt("gender"));
				reqj.setHeadcount(rs.getInt("headcount"));
				reqj.setCurrent_latitude(rs.getDouble("current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("current_longitude"));
				reqj.setDrop_off_latitude(rs.getDouble("drop_off_latitude"));
				reqj.setDrop_off_longitude(rs.getDouble("drop_off_longitude"));
				reqj.setRegistration_date(rs.getString("registration"));
				
				reqjList.add(reqj);
			}

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
		} catch (SQLException e) {
			e.printStackTrace();
			reqjList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reqjList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reqjList = null;
				}
			}
		}
		return reqjList;
	}
	
	
	public List<RequestJoin> searchMyApprove(RequestJoin reqj) {
		Connection conn = null;
		List<RequestJoin> reqjList = new ArrayList<RequestJoin>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select nickname, StandByUser.current_latitude, StandByUser.current_longitude, Request.current_latitude, Request.current_longitude from Request join User on Request.id= User.id join StandByUser on Request.stand_by_id = StandByUser.stand_by_id where partner_id = ? and status = 1;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, );

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				reqj.setNickname(rs.getString("nickname"));
				reqj.setPrtnr_current_latitude(rs.getDouble("StandByUser.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("StandByUser.current_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("Request.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("Request.current_longitude"));
				
				reqjList.add(reqj);
			}

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
		} catch (SQLException e) {
			e.printStackTrace();
			reqjList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reqjList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reqjList = null;
				}
			}
		}
		return reqjList;
	}
	
	
	public List<RequestJoin> searchApproved(RequestJoin reqj) {
		Connection conn = null;
		List<RequestJoin> reqjList = new ArrayList<RequestJoin>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select nickname, StandByUser.current_latitude, StandByUser.current_longitude, Request.current_latitude, Request.current_longitude from Request join User on Request.id= User.id join StandByUser on Request.stand_by_id = StandByUser.stand_by_id where id = ? and status = 1;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, );

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				reqj.setNickname(rs.getString("nickname"));
				reqj.setPrtnr_current_latitude(rs.getDouble("StandByUser.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("StandByUser.current_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("Request.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("Request.current_longitude"));
				
				reqjList.add(reqj);
			}
			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
		} catch (SQLException e) {
			e.printStackTrace();
			reqjList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reqjList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reqjList = null;
				}
			}
		}
		return reqjList;
	}
	
	
	public boolean deleteRequest(Request req) {
		boolean deleteResult = false;
		int deleteCount = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			String countSql = "select count(*) from Request where status = 2;";
			PreparedStatement cPStmt = conn.prepareStatement(countSql);
			// SELECT文を準備する
			String sql = "delete from Request where status = 2;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = cPStmt.executeQuery();
			rs.next();
			deleteCount = rs.getInt("count(*)");
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == deleteCount) {
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
		
		return deleteResult;
	}
}
