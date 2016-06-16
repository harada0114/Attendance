package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ErrorMessage.MsgLeaving;
import model.Time;

public class TimeDAO {
	
	// 出社
	// trueで出社できる、falseで出社できない
	public boolean canAdmission(Time time) throws ClassNotFoundException,SQLException {
		
		Connection conn = null;	

		try {
			Class.forName("com.mysql.jdbc.Driver");			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
			
			String sql = "SELECT admission FROM time WHERE mail=? AND day=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, time.getMail());
			pStmt.setString(2, time.getDay());
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("出社が被っています。エラーです");
		  		return false; // 出勤が被っています。OUT
			}
			
			else {			
				String sql1 = "INSERT INTO time(mail,day,admission) VALUES(?,?,?)";
				PreparedStatement pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setString(1, time.getMail());
				pStmt1.setString(2, time.getDay());
				pStmt1.setString(3, time.getAdmission());
			
				int result = pStmt1.executeUpdate();
			
				// もし一件もINSERTできなければ
				if (result != 1) {	
					System.out.println("INSERTできませんでした。エラーです");
					return false;
				}
			}
		} finally {
			if (conn != null) {
				conn.close();
			}	
		}
		return true; // 出勤OK
	}
			

	
	// 退社
	// 実行状態によりメッセージを返す
	public MsgLeaving runLeaving(Time time) throws ClassNotFoundException,SQLException {
		
		Connection conn = null;
		String leaving = "";
		int count = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");	
			
			String sql = "SELECT leaving FROM time WHERE mail=? AND day=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, time.getMail());
			pStmt.setString(2, time.getDay());
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) { // ヒットした件数分回す
				leaving = rs.getString("leaving");
				count++;
				System.out.println("退社検索ヒットしました。leaving ("+leaving+")、count ("+count+")");
			}
			
			// ヒットしていない場合、ここからの処理になる。
	
			// 出社でINSERTされていません。
			if (count == 0) {
				System.out.println("出社されていません。leaving ("+leaving+")、count ("+count+")");
				return MsgLeaving.NOT_ADMISSION;
			}
			// 2件以上はDBの内部的エラーです。
			if (count >= 2) {
				System.out.println("DBエラーです。leaving ("+leaving+")、count ("+count+")");
				return MsgLeaving.SYSTEM_ERROR;
			}
			// 退社済みです。
			if (count == 1 && leaving != null) {
				System.out.println("退社済みです。leaving ("+leaving+")、count ("+count+")");
				return MsgLeaving.ALREADY;
			}
			
			else {
				// 退社準備OK	
				System.out.println("退社OK。leaving ("+leaving+")、count ("+count+")");
				
				String sql2 = "UPDATE time SET leaving=? WHERE mail=? AND day=?";		
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
				pStmt2.setString(1, time.getLeaving());
				pStmt2.setString(2, time.getMail());
				pStmt2.setString(3, time.getDay());
						    	
				int result = pStmt2.executeUpdate();

				// もしも更新できなければ
				if (result != 1) {
					System.out.println("更新ができませんでした。");
					return MsgLeaving.SYSTEM_ERROR;
				}
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return MsgLeaving.OK; // 退社OK
	}
			
	
	
	// 一覧表示処理
	// DBから取得したレコードを格納したインスタンスを返す
	public List<Time> findAll(Time time) throws ClassNotFoundException,SQLException {
			
		List<Time> timeList = new ArrayList<Time>();
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");				
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
		
			// メールアドレスで検索し全レコードを取得
			String sql = "SELECT mail,day,admission,leaving FROM time WHERE mail=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, time.getMail());
			    			
			ResultSet rs = pStmt.executeQuery();
			    
			// 取得できれば結果をArrayListに格納
			while (rs.next()) {
				String mail = rs.getString("mail");
				String day = rs.getString("day");
				String admission = rs.getString("admission");
				String leaving = rs.getString("leaving");

				Time find_time = new Time(mail, day, admission, leaving);
			    	
				// timeListにレコードを順番に詰める
				timeList.add(find_time);
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return timeList; // DBから取得したレコードが入ったtimeListを返す
	}
}