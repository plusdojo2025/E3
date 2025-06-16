package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Taxi;

public class TaxiDao {
	// メアドとパスワードを登録します。
			public boolean insertTaxi(Taxi taxi) {
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
				String sql = "insert into Taxi values(?, ?, ?, ?, ?, ?, ?,);";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, taxi.getTaxinumber());
				pStmt.setString(2, taxi.getCompany());
				pStmt.setString(3, taxi.getPhone());
				pStmt.setDouble(4, taxi.getTaxi_address_latitude());
				pStmt.setDouble(5, taxi.getTaxi_address_longitude());
				pStmt.setInt(6, taxi.getPrice());
				pStmt.setString(7, taxi.getTaxi_address());

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
	public boolean loginCheck(Taxi taxi) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select taxinumber = ?, company = ?, phone = ?, taxi_address_latitude = ?, taxi_address_longitude =?, price =? taxi_address =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			if (taxi.getTaxinumber() != 0) {
				pStmt.setInt(1, taxi.getTaxinumber());
			} else {
				pStmt.setString(1, "null");
			}
			
			if (taxi.getCompany() != null) {
				pStmt.setString(2, taxi.getCompany());
			} else {
				pStmt.setString(2, "null");
			}
		
			if (taxi.getPhone() != null) {
				pStmt.setString(3, taxi.getPhone());
			} else {
				pStmt.setString(3, "null");
			}
			
				pStmt.setDouble(4, taxi.getTaxi_address_latitude());

				pStmt.setDouble(5, taxi.getTaxi_address_longitude());

			
			if (taxi.getPrice() != 0) {
				pStmt.setInt(6, taxi.getPrice());
			} else {
				pStmt.setInt(6, 0);
			}
			
			if (taxi.getTaxi_address() != null) {
				pStmt.setString(7, taxi.getTaxi_address());
			} else {
				pStmt.setString(7, "null");
			}
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				loginResult = true;
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
		return loginResult;
	}
}
