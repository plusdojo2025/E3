package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Request;
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
	
	public List<Request> searchRequestMe(Request req) {
		Connection conn = null;
		List<Request> reqList = new ArrayList<Request>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select name, nickname, gender, address_latitude, address_longitude, partner_gender, smoking, talking from User where id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			user.setName(rs.getString("name")); 
			user.setNickname(rs.getString("nickname")); 
			user.setGender(rs.getInt("gender")); 
			user.setAddress_latitude(rs.getDouble("address_latitude")); 
			user.setAddress_longitude(rs.getDouble("address_longitude")); 
			user.setPartner_gender(rs.getInt("partner_gender")); 
			user.setSmoking(rs.getInt("smoking")); 
			user.setTalking(rs.getInt("talking")); 
				
			
		} catch (SQLException e) {
			e.printStackTrace();
			user = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			user = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					user = null;
				}
			}
		}
		return user;
	}
}
