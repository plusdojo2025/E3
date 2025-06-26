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
			String sql = "insert into request values( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, req.getId());
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
			pStmt.setString(14, req.getDate());
							
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
	
	public boolean updateRequest(int id, int status) {
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
			String sql = "update request set status = ? where request_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, status);
			pStmt.setInt(2, id);
			
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
	
	public List<RequestJoin> searchRequestMe(int id) {
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
			String sql = "select Request.id, nickname, gender, Request.headcount, request.current_latitude, request.current_longitude, request.drop_off_latitude, request.drop_off_longitude, standbyuser.current_latitude, standbyuser.current_longitude, standbyuser.drop_off_latitude, standbyuser.drop_off_longitude, Request.registration_date, standbyuser.date  from request join user on request.id = user.id join standbyuser on request.partner_id = standbyuser.id where partner_id = ? and status = 0;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				RequestJoin reqj = new RequestJoin();
				reqj.setId(rs.getInt("Request.id"));
				reqj.setNickname(rs.getString("nickname"));
				reqj.setGender(rs.getInt("gender"));
				reqj.setHeadcount(rs.getInt("Request.headcount"));
				reqj.setPrtnr_current_latitude(rs.getDouble("request.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("request.current_longitude"));
				reqj.setPrtnr_drop_off_latitude(rs.getDouble("request.drop_off_latitude"));
				reqj.setPrtnr_drop_off_longitude(rs.getDouble("request.drop_off_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("standbyuser.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("standbyuser.current_longitude"));
				reqj.setDrop_off_latitude(rs.getDouble("standbyuser.drop_off_latitude"));
				reqj.setDrop_off_longitude(rs.getDouble("standbyuser.drop_off_longitude"));
				reqj.setRegistration_date(rs.getString("Request.registration_date"));
				reqj.setDesired_date(rs.getString("standbyuser.date"));
				
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
	
	// ログインユーザーが待機者か申請者かチェックして情報取得
	public RequestJoin getRequestInfo(int loginUserId, int requestId) {
		Connection conn = null;
		int id = 0; 
		RequestJoin reqj = new RequestJoin();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select id from request where request_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, requestId);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("id");
			}

			// リクエスト情報の申請者idとログインユーザーのidを比較
			if (id == loginUserId) {		// ログインユーザーが申請者の場合
				reqj = searchApproved(requestId);
			} else {						// ログインユーザーが待機者の場合
				reqj = searchMyApprove(requestId); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			reqj = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reqj = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reqj = null;
				}
			}
		}
		return reqj;
	}
	
	// ログインユーザーが待機者の場合の相手のニックネーム, 現在地, 自分の現在地取得処理
	public RequestJoin searchMyApprove(int requestId) {
		Connection conn = null;
		RequestJoin reqj = new RequestJoin();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select nickname, standbyuser.current_latitude, standbyuser.current_longitude, request.current_latitude, request.current_longitude from request join user on request.id= user.id join standbyuser on request.stand_by_id = standbyuser.stand_by_id where request_id = ? and status = 1;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, requestId);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				reqj.setNickname(rs.getString("nickname"));
				reqj.setPrtnr_current_latitude(rs.getDouble("request.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("request.current_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("standbyuser.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("standbyuser.current_longitude"));
			}

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
		} catch (SQLException e) {
			e.printStackTrace();
			reqj = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reqj = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reqj = null;
				}
			}
		}
		return reqj;
	}
	
	// ログインユーザーが申請者の場合の相手のニックネーム, 現在地, 自分の現在地取得処理
	public RequestJoin searchApproved(int requestId) {
		Connection conn = null;
		RequestJoin reqj = new RequestJoin();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "select nickname, standbyuser.current_latitude, standbyuser.current_longitude, request.current_latitude, request.current_longitude from request join user on request.partner_id= user.id join standbyuser on request.stand_by_id = standbyuser.stand_by_id where request_id = ? and status = 1;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, requestId);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				reqj.setNickname(rs.getString("nickname"));
				reqj.setPrtnr_current_latitude(rs.getDouble("standbyuser.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("standbyuser.current_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("request.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("request.current_longitude"));
			}
			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
		} catch (SQLException e) {
			e.printStackTrace();
			reqj = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reqj = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reqj = null;
				}
			}
		}
		return reqj;
	}
	
	
	public List<RequestJoin> searchForNotice(int id){
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
			String sql = "select id, nickname, gender, headcount, "
					+ "current_latitude, current_longitude, drop_off_latitude, drop_off_longitude, registration_date, date"
					
					+ "from request "
					+ "join user on request.id = user.id "
					+ "where (partner_id = ? and (status = 0 or status = 1)) or (id = ? and status = 1);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setInt(2, id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				RequestJoin reqj = new RequestJoin();
				reqj.setId(rs.getInt("id"));
				reqj.setNickname(rs.getString("nickname"));
				reqj.setGender(rs.getInt("gender"));
				reqj.setHeadcount(rs.getInt("headcount"));
				reqj.setCurrent_latitude(rs.getDouble("current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("current_longitude"));
				reqj.setDrop_off_latitude(rs.getDouble("drop_off_latitude"));
				reqj.setDrop_off_longitude(rs.getDouble("drop_off_longitude"));
				reqj.setRegistration_date(rs.getString("registration_date"));
				reqj.setRegistration_date(rs.getString("Date"));
				
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

	public List<RequestJoin> sendForNotice(int id){
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
			String sql = "select partner_id, nickname, gender, standbyuser.headcount, request.current_latitude, "
					+"request.current_longitude, request.drop_off_latitude, "
					+ "request.drop_off_longitude, standbyuser.current_latitude, "
					+"standbyuser.current_longitude, standbyuser.drop_off_latitude, "
					+"standbyuser.drop_off_longitude, request.registration_date, standbyuser.date  "
					+"from standbyuser "
					+"join user on standbyuser.id = user.id "
					+"join request on request.partner_id = standbyuser.id "
					+"where request.id = ? and status = 1;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				RequestJoin reqj = new RequestJoin();
				reqj.setId(rs.getInt("partner_id"));
				reqj.setNickname(rs.getString("nickname"));
				reqj.setGender(rs.getInt("gender"));
				reqj.setHeadcount(rs.getInt("standbyuser.headcount"));
				reqj.setPrtnr_current_latitude(rs.getDouble("standbyuser.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("standbyuser.current_longitude"));
				reqj.setPrtnr_drop_off_latitude(rs.getDouble("standbyuser.drop_off_latitude"));
				reqj.setPrtnr_drop_off_longitude(rs.getDouble("standbyuser.drop_off_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("request.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("request.current_longitude"));
				reqj.setDrop_off_latitude(rs.getDouble("request.drop_off_latitude"));
				reqj.setDrop_off_longitude(rs.getDouble("request.drop_off_longitude"));
				reqj.setRegistration_date(rs.getString("request.registration_date"));
				reqj.setDesired_date(rs.getString("standbyuser.date"));
				
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
	

	public List<RequestJoin> respForNotice(int id){
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
			String sql = "select Request.id, nickname, gender, Request.headcount, request.current_latitude, "
					+"request.current_longitude, request.drop_off_latitude, "
					+ "request.drop_off_longitude, standbyuser.current_latitude, "
					+"standbyuser.current_longitude, standbyuser.drop_off_latitude, "
					+"standbyuser.drop_off_longitude, standbyuser.registration_date, request.date  "
					+"from request "
					+"join user on request.id = user.id "
					+"join standbyuser on request.partner_id = standbyuser.id "
					+ "where request.partner_id = ? and status = 1;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				RequestJoin reqj = new RequestJoin();
				reqj.setId(rs.getInt("request.id"));
				reqj.setNickname(rs.getString("nickname"));
				reqj.setGender(rs.getInt("gender"));
				reqj.setHeadcount(rs.getInt("headcount"));
				reqj.setPrtnr_current_latitude(rs.getDouble("request.current_latitude"));
				reqj.setPrtnr_current_longitude(rs.getDouble("request.current_longitude"));
				reqj.setPrtnr_drop_off_latitude(rs.getDouble("request.drop_off_latitude"));
				reqj.setPrtnr_drop_off_longitude(rs.getDouble("request.drop_off_longitude"));
				reqj.setCurrent_latitude(rs.getDouble("standbyuser.current_latitude"));
				reqj.setCurrent_longitude(rs.getDouble("standbyuser.current_longitude"));
				reqj.setDrop_off_latitude(rs.getDouble("standbyuser.drop_off_latitude"));
				reqj.setDrop_off_longitude(rs.getDouble("standbyuser.drop_off_longitude"));
				reqj.setRegistration_date(rs.getString("standbyuser.registration_date"));
				reqj.setDesired_date(rs.getString("request.date"));
				
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
			
			String countSql = "select count(*) from request where status = 2;";
			PreparedStatement cPStmt = conn.prepareStatement(countSql);
			// SELECT文を準備する
			String sql = "delete from request where status = 2;";
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
