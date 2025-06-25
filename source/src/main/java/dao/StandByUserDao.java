package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dto.StandByUser;
import dto.StandByUserJoin;

public class StandByUserDao {
	// 必要事項登録　検索時
	public boolean insertStandByUser(StandByUser standByUser) {
		Connection conn = null;
		boolean insertResult = false;			// trueの場合は成功, falseの場合は失敗
		
		try {
			// jdbcドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			String sql = "insert into standbyuser values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
					+ "registration_date, date from standbyuser "
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
	
	// searchSandByInfo用
	public StandByUser getMyStandInfo(int id) {
		Connection conn = null;
		// 取得データを格納するリスト
		StandByUser myInfo = new StandByUser();
		
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
					+ "registration_date, date from standbyuser "
					+ "where id = ? and flag = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			// SQL文を実行して検索結果を取得
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				// 検索結果をコレクションに格納
				myInfo.setHeadcount(rs.getInt("headcount"));
				myInfo.setCurrent_latitude(rs.getDouble("current_latitude"));
				myInfo.setCurrent_longitude(rs.getDouble("current_longitude"));
				myInfo.setDrop_off_latitude(rs.getDouble("drop_off_latitude"));
				myInfo.setDrop_off_longitude(rs.getDouble("drop_off_longitude"));
				myInfo.setRegistration_date(rs.getString("registration_date"));
				myInfo.setDate(rs.getString("date"));
			}

			// 検索結果が格納されたコレクションを返す
			return myInfo;
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
	public List<StandByUserJoin> searchStandByInfo(int id) {
		Connection conn = null;
		List<StandByUserJoin> sbujList = new ArrayList<StandByUserJoin>();
		StandByUser myStandInfo = getMyStandInfo(id);

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文
			if(getMyStandInfo(id).getPartner_gender() == 1) { // 自分が同性を希望している場合
				String sql =
						"SELECT * FROM ("
						+ "SELECT nickname, gender, headcount, current_latitude, current_longitude, drop_off_latitude, drop_off_longitude, registration_date,"
						+ "(6371 * acos(cos(radians(?)) * cos(radians(current_latitude))"
						+ "* cos(radians(current_longitude) - radians(?))"
						+ "+ sin(radians(?)) * sin(radians(current_latitude)))) AS cur_distance,"

						+ "(6371 * acos(cos(radians(?)) * cos(radians(drop_off_latitude))"
						+ "* cos(radians(drop_off_longitude) - radians(?))"
						+ "+ sin(radians(?)) * sin(radians(drop_off_latitude)))) AS drop_distance,"

						+ "(headcount + ?) AS sum_headcount "
						+ "FROM standbyuser "
						+ "JOIN user ON standbyuser.id = user.id "
						+ "WHERE flag = 1 AND date <= ? AND date >= ? "
						+ "AND user.gender = ? "
						+ "AND (? = 0 OR (? = 1 AND smoking = 1)) "		//喫煙
						+ "AND talking = ?"			//会話
						+ ") AS filtered "
						+ "WHERE cur_distance < 1 AND drop_distance < 5 AND sum_headcount <= 3;"; //出発地1km圏内、目的地5km圏内
				
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setDouble(1, myStandInfo.getCurrent_latitude());
				pStmt.setDouble(2, myStandInfo.getCurrent_longitude());
				pStmt.setDouble(3, myStandInfo.getCurrent_latitude());
				pStmt.setDouble(4, myStandInfo.getDrop_off_latitude());
				pStmt.setDouble(5, myStandInfo.getDrop_off_longitude());
				pStmt.setDouble(6, myStandInfo.getDrop_off_latitude());
				pStmt.setInt(7, myStandInfo.getHeadcount());
				pStmt.setString(8, calculateDate(myStandInfo.getDate(), 20)); //ユーザーの希望日時の20分後を8つ目の?に代入
				pStmt.setString(9, calculateDate(myStandInfo.getDate(), -20)); //ユーザーの希望日時の20分前を9つ目の?に代入
				pStmt.setInt(10, new UserDao().searchUser(id).getGender()); //ユーザーの性別を10つ目の?に代入
				pStmt.setInt(11, myStandInfo.getSmoking()); //非喫煙を11つ目の?に代入
				pStmt.setInt(12, myStandInfo.getSmoking()); //非喫煙を12つ目の?に代入
				pStmt.setInt(13, myStandInfo.getTalking()); //非会話を13つ目の?に代入

				ResultSet rs = pStmt.executeQuery();
				while(rs.next()) {
					StandByUserJoin sbuj = new StandByUserJoin();
					sbuj.setNickname(rs.getString("nickname"));
					sbuj.setGender(rs.getInt("gender"));
					sbuj.setHeadcount(rs.getInt("headcount"));
					sbuj.setCurrent_latitude(rs.getDouble("current_latitude"));
					sbuj.setCurrent_longitude(rs.getDouble("current_longitude"));
					sbuj.setDrop_off_latitude(rs.getDouble("drop_off_latitude"));
					sbuj.setDrop_off_longitude(rs.getDouble("drop_off_longitude"));
					sbuj.setRegistration_date(rs.getString("registration_date"));
						
					sbujList.add(sbuj);
				}
			}
			else { // 自分が同性を希望していない場合
				String sql = "select nickname, gender, headcount, current_latitude, current_longitude, drop_off_latitude, drop_off_longitude, registration_date,"
				        + "(6371 * acos(cos(radians(?)) * cos(radians(current_latitude))"
				        + "* cos(radians(current_longitude) - radians(?))"
				        + "+ sin(radians(?)) * sin(radians(current_latitude)))) as cur_distance,"  // ← 最後に "))" を追加

				        + "(6371 * acos(cos(radians(?)) * cos(radians(drop_off_latitude))"
				        + "* cos(radians(drop_off_longitude) - radians(?))"
				        + "+ sin(radians(?)) * sin(radians(drop_off_latitude)))) as drop_distance,"  // ← 同様に "))" に修正
						
						+ "(headcount + ?) as sum_headcount from standbyuser "

						+ "join user on standbyuser.id = user.id "
						
						+ "where flag = 1 and date <= ? and date >= ? "
						+ "and ((user.partner_gender = 1 and user.gender = ?) or user.partner_gender = 0)" //性別
						+ "and (? = 0 or (? = 1 and user.smoking = 1))" //喫煙
						+ "and user.talking = ? " //会話
						
						+ "having cur_distance < 1 and drop_distance < 5 and sum_headcount <= 3;"; //出発地1km圏内、目的地5km圏内
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setDouble(1, myStandInfo.getCurrent_latitude());
			pStmt.setDouble(2, myStandInfo.getCurrent_longitude());
			pStmt.setDouble(3, myStandInfo.getCurrent_latitude());
			pStmt.setDouble(4, myStandInfo.getDrop_off_latitude());
			pStmt.setDouble(5, myStandInfo.getDrop_off_longitude());
			pStmt.setDouble(6, myStandInfo.getDrop_off_latitude());
			pStmt.setDouble(7, myStandInfo.getHeadcount());
			pStmt.setString(8, calculateDate(myStandInfo.getDate(), 20)); //ユーザーの希望日時の20分後を8つ目の?に代入
			pStmt.setString(9, calculateDate(myStandInfo.getDate(), -20)); //ユーザーの希望日時の20分前を9つ目の?に代入
			pStmt.setInt(10, new UserDao().searchUser(id).getGender()); //ユーザーの性別を10つ目の?に代入
			pStmt.setInt(11, myStandInfo.getSmoking()); //非喫煙を11つ目の?に代入
			pStmt.setInt(12, myStandInfo.getSmoking()); //非喫煙を12つ目の?に代入
			pStmt.setInt(13, myStandInfo.getTalking()); //非会話を13つ目の?に代入
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				StandByUserJoin sbuj = new StandByUserJoin();
				sbuj.setNickname(rs.getString("nickname"));
				sbuj.setGender(rs.getInt("gender"));
				sbuj.setHeadcount(rs.getInt("headcount"));
				sbuj.setCurrent_latitude(rs.getDouble("current_latitude"));
				sbuj.setCurrent_longitude(rs.getDouble("current_longitude"));
				sbuj.setDrop_off_latitude(rs.getDouble("drop_off_latitude"));
				sbuj.setDrop_off_longitude(rs.getDouble("drop_off_longitude"));
				sbuj.setRegistration_date(rs.getString("registration_date"));
					
				sbujList.add(sbuj);
			}
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
		
		return sbujList;
	}
	
	// 待機情報flag更新
	public boolean updateFlag(int id) {
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
			String sql = "update standbyuser set flag = 1, set talking = ?, set smoking = ?, set partner_gender = ? "
					+ "where id = ? "
					+ "order by stand_by_id desc limit 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, new UserDao().searchUser(id).getTalking());
			pStmt.setInt(2, new UserDao().searchUser(id).getSmoking());
			pStmt.setInt(3, new UserDao().searchUser(id).getPartner_gender());
			pStmt.setInt(4, id);

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
			String countSql = "select count(*) from standbyuser where date < curdate();";
			PreparedStatement cPStmt = conn.prepareStatement(countSql);
			// 削除するSQL
			String deleteSql = "delete from standbyuser where date < curdate();";
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
	public boolean deleteStandByInfo(int StandById) {
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
			String sql = "delete from standbyuser where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, StandById);

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
	
	public String getDate(int sbid) {
		Connection conn = null;
		String date = "";
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文
			String sql = "select date from standbyuser where stand_by_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, sbid);

			ResultSet rs = pStmt.executeQuery();
			date = rs.getString("date");
			
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
		return date;	}
	
	// XXXX/XX/XX XX:XX の時間を進退させて同じ型に戻しますメソッド
	public static String calculateDate(String bef, int c) {
		String aft = "";
		try {
			// 入力の形式に合わせたフォーマットを使う
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm"); // 出力フォーマット

			Date date = inputFormat.parse(bef);
			Calendar clndr = Calendar.getInstance();
			clndr.setTime(date);
			clndr.add(Calendar.MINUTE, c);

			aft = outputFormat.format(clndr.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return aft;
	}

}
