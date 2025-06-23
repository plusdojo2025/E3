package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.IdPw;
import dto.ResultUserID;

public class IdPwDao {
	// メアドとパスワードを登録します。
	public ResultUserID insertIdpw(IdPw idpw) {
		Connection conn = null;
		boolean insertResult = false;
		int generatedId = -1;	//初期値-1(エラー)

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// INSERT文を準備する
			String sql = "insert into idpw values(0, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, idpw.getPass());
			pStmt.setString(2, idpw.getEmail());
							
			if (pStmt.executeUpdate() == 1) {
				insertResult = true;
				//自動採番されたidを取得
				try (ResultSet rs = pStmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    generatedId = rs.getInt(1);
	                }
	            }
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

		// 登録野結果と自動採番されたidを返す
		return new ResultUserID(generatedId, insertResult);
	}
		
	//ログインできるときはtrueを返しますよ。
	public ResultUserID loginCheck(IdPw idpw) {
		Connection conn = null;
		boolean loginResult = false;
		int generatedId = -1;	//初期値-1(エラー)

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "SELECT count(*) FROM idpw WHERE email=? AND pass=?";
			PreparedStatement pStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, idpw.getEmail());
			pStmt.setString(2, idpw.getPass());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
				//自動採番されたidを取得
				try (ResultSet lrs = pStmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    generatedId = lrs.getInt(1);
	                }
				}
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
		return new ResultUserID(generatedId, loginResult);
	}
}
