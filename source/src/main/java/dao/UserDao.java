package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserDao {
	// メアドとパスワードを登録します。
	public boolean insertUser(User user) {
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
			String sql = "insert into User values(?, ?, ?, ?, ?, ?, ?, ?, ?, );";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, );
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getNickname());
			pStmt.setInt(4, user.getGender());
			pStmt.setDouble(5, user.getAddress_latitude());
			pStmt.setDouble(6, user.getAddress_longitude());
			pStmt.setInt(7, user.getTalking());
			pStmt.setInt(8, user.getSmoking());
			pStmt.setInt(9, user.getPartner_gender());
							
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
	
	//ログインできるときはtrueを返しますよ。
	public User loginCheck(int id) {
		Connection conn = null;
		User user = new User();

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

		// 結果を返す
		return user;
	}
}
