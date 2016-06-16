// テーブル毎に作成
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Staff;
import model.Login;

public class StaffDAO {
	
	// 静的初期化ブロック。このクラスが利用されるときに一度だけ実行される。
	// ドライバのロードは1度だけ実行されれば良い。
	// ？
	
	/*
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/
	
	// メールアドレス重複チェックメソッド
	public boolean isDoubleMail(String mail) throws ClassNotFoundException,SQLException {
		
		Connection conn = null;
		
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
			
			// 入力されたメールアドレスで検索
			String sql = "SELECT mail FROM staff WHERE mail=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mail);
							
			ResultSet rs = pStmt.executeQuery();
						
			// 同じメールアドレスが取得できれば
			if (rs.next()) {
				return true;
			} 
		
		// 必ずDBを切断
		} finally { 
			if (conn != null) {
				conn.close();
			}
		}
		return false;		
	}	
		
	// 新規登録メソッド
	public boolean createStaff(Staff staff) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
		    
			// メール、パス、名前でレコード追加
			String sql = "INSERT INTO staff(mail,pass,name) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, staff.getMail());
			pStmt.setString(2, staff.getPass());
			pStmt.setString(3, staff.getName());
		
			int result = pStmt.executeUpdate();
		
			// 追加できなければ
			if (result != 1) {
				return false;
			}
			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}
		
		
	// ログインメソッド
	public Staff findByLogin(Login login) throws ClassNotFoundException, SQLException {
		
		Staff staff = null;
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
			
			String sql = "SELECT mail, pass, name FROM staff WHERE mail=? AND pass=?";
		    PreparedStatement pStmt = conn.prepareStatement(sql);
		    pStmt.setString(1, login.getMail());
		    pStmt.setString(2, login.getPass());
			
		    ResultSet rs = pStmt.executeQuery();
		    
		    // 一致したユーザが取得できれば
		    if (rs.next()) { 
		    	String mail = rs.getString("mail");
		    	String pass = rs.getString("pass");
		    	String name = rs.getString("name");
		    	
		    	staff = new Staff(mail,pass,name);
		    }

		} finally {
			if (conn != null) {
				conn.close();	
			}
		}
		return staff;
	}
}