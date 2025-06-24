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
			String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getId());
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getNickname());
			pStmt.setInt(4, user.getGender());
			pStmt.setDouble(5, user.getAddress_latitude());
			pStmt.setDouble(6, user.getAddress_longitude());
			pStmt.setInt(7, user.getTalking());
			pStmt.setInt(8, user.getSmoking());
			pStmt.setInt(9, user.getPartner_gender());
			pStmt.setString(10, user.getAddress());
							
			//SQL文を実行
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
	public User searchUser(int id) {
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
			String sql = "select name, nickname, gender, address_latitude, address_longitude, partner_gender, smoking, talking, address from user where id = ?;";
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
			user.setAddress(rs.getString("address"));
			
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
	
	//ログインできるときはtrueを返しますよ。
	public boolean updateUser(User user, int id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "update user set name = ?, nickname = ?, gender = ?, address_latitude = ?, address_longitude = ?, partner_gender = ?, smoking = ?, talking = ? where id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

		
			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			if (user.getName() != null) {
				pStmt.setString(1, user.getName());
			} else {
				pStmt.setString(1, "null");
			}
			
			if (user.getNickname() != null) {
				pStmt.setString(2, user.getNickname());
			} else {
				pStmt.setString(2, "null");
			}
		
			pStmt.setInt(3, user.getGender());
		
			pStmt.setDouble(4, user.getAddress_latitude());
		
			pStmt.setDouble(5, user.getAddress_longitude());
		
			pStmt.setInt(6, user.getPartner_gender());
		
			pStmt.setInt(7, user.getSmoking());

			pStmt.setInt(8, user.getTalking());

			pStmt.setInt(9, id);
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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
		return result;
	}
}
