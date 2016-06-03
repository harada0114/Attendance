package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Time;

public class TimeDAO {
	
	// DB出社処理
	public boolean admission(Time time) throws ClassNotFoundException,SQLException {
		Connection conn = null;		

			Class.forName("com.mysql.jdbc.Driver");
						
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
							
			// 同日で出社済みか検索。連続して出社できない。
			String sql = "SELECT admission FROM time WHERE mail=? AND day=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, time.getMail());
			pStmt.setString(2, time.getDay());
				
			ResultSet rs = pStmt.executeQuery();
			
			// 同日の出社レコードが取得できれば
			if (rs.next()) {
				//String admission = rs.getString("admission");			
		  		return false;
		  	}
		  	
			String sql2 = "INSERT INTO time(mail,day,admission) VALUES(?,?,?)";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			pStmt2.setString(1, time.getMail());
			pStmt2.setString(2, time.getDay());
			pStmt2.setString(3, time.getAdmission());
			
			int result = pStmt2.executeUpdate();
			
			// もしすでにレコードがあり、一件もINSERTできなければ
			if (result != 1) {	
				return false;
			}
		
			if (conn != null) {
				conn.close();
				
			}	
		return true; // 出社可能です			
		
	}
	
	// DB退社処理		
	public int leaving(Time time) throws ClassNotFoundException,SQLException {
		Connection conn = null;
		
			Class.forName("com.mysql.jdbc.Driver");
						
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
				
			// 同日で退社済みか検索。連続して退社できない。
			String sql3 = "SELECT leaving FROM time WHERE mail=? AND day=?";
			PreparedStatement pStmt3 = conn.prepareStatement(sql3);
			pStmt3.setString(1, time.getMail());
			pStmt3.setString(2, time.getDay());
				
			ResultSet rs3 = pStmt3.executeQuery();
			    
			// 同日の退社レコードが取得できれば
			if (rs3.next()) {
				String leaving = rs3.getString("leaving");
			    	
				// 取得したleavingの値がnullじゃないならすでに出社済み。// null直したい
				if (leaving != null) {
				  return 1;
				}
			}
			
			// ログインしているメールアドレスと日付が同じ出社レコードを検索して、
			// 一致したレコードがあれば退社を更新する。
			String sql4 = "SELECT mail, day FROM time WHERE mail=? AND day=?";
			PreparedStatement pStmt4 = conn.prepareStatement(sql4);
			pStmt4.setString(1, time.getMail());
			pStmt4.setString(2, time.getDay());
	
		    ResultSet rs4 = pStmt4.executeQuery();
			    
			// もし同日の出社レコードが一致した場合
			if (rs4.next()) {
				String mail = rs4.getString("mail");
				String day = rs4.getString("day");
					    	
			    time = new Time(mail,day,time.getAdmission(),time.getLeaving());
			    
				String sqlB = "UPDATE time SET leaving=? WHERE mail=? AND day=?";		
				PreparedStatement pStmtB = conn.prepareStatement(sqlB);
				pStmtB.setString(1, time.getLeaving());
				pStmtB.setString(2, time.getMail());
				pStmtB.setString(3, time.getDay());
					    	
				int result = pStmtB.executeUpdate();
			
				// もし、一件も更新できなければ
				if (result != 1) {
					return 0;
				}
				
			// もし同日の出社レコードが一致しなかった場合
			} else {
				return 2;
			}
			
			if (conn != null) {
				conn.close();
			}
		return 3; // 退社登録OK		
	}
			
	// DB一覧表示処理
		public List<Time> findAll(Time time) throws ClassNotFoundException,SQLException {
			Connection conn = null;
			
			List<Time> timeList = new ArrayList<Time>();
		
				Class.forName("com.mysql.jdbc.Driver");
							
				conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
				
				String sql = "SELECT mail,day,admission,leaving FROM time WHERE mail=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
			    pStmt.setString(1, time.getMail());
			    			
			    ResultSet rs = pStmt.executeQuery();
			    
			    // 結果をArrayListに格納
			    while (rs.next()) { // 回す
			    	String mail = rs.getString("mail");
			    	String day = rs.getString("day");
			    	String admission = rs.getString("admission");
			    	String leaving = rs.getString("leaving");

			    	Time timeC = new Time(mail,day,admission,leaving); //timeCにレコードが詰まってる
			    	
			    	// timeListにレコードを順番に詰める
			    	timeList.add(timeC);
			    }
			// 例外処理
				if (conn != null) {
						conn.close();
			
				}
			
		
			return timeList;
}}