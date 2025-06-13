package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.IdPw;

public class IdPwDao {
		// メアドとパスワードを登録します。
		public boolean insertIdpw(IdPw idpw) {
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
				String sql = "insert into IdPw values(0, ?, ?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, idpw.getPass());
				pStmt.setString(2, idpw.getEmail());
								
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
		public boolean loginCheck(IdPw idpw) {
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
				String sql = "SELECT count(*) FROM IdPw WHERE email=? AND pass=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, idpw.getEmail());
				pStmt.setString(2, idpw.getPass());

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
				rs.next();
				if (rs.getInt("count(*)") == 1) {
					loginResult = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return loginResult;
		}
}
