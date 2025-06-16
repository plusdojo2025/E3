package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Chat;

public class ChatDao {
	// チャット送信時の必要事項を登録する
	public boolean insertChat(Chat chat) {
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
			String sql = "insert into Chat values(?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, chat.getId());
			pStmt.setInt(2, chat.getRoomId());
			pStmt.setInt(3, chat.getSenderId());
			pStmt.setString(4, chat.getChatText());
			pStmt.setString(5, chat.getChatDate());
							
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
	
	//チャット内容を表示する
	public List<Chat> displayChat(int roomId) {
		Connection conn = null;
		List<Chat> chatList = new ArrayList<Chat>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select sender_id, chat_text, chat_date from Chat where room_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, roomId);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 検索結果をコレクションに格納
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setSenderId(rs.getInt("sender_id"));
				chat.setChatText(rs.getString("chat_text"));
				chat.setChatDate(rs.getString("chat_date"));
				chatList.add(chat);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				chatList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				chatList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						chatList = null;
					}
				}
		}

		// 結果を返す
		return chatList;
	}


}
