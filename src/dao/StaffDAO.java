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
		
	// メールアドレス重複チェックメソッド
	// アドレスがヒットすればtrue、しなければfalse
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
	
	
		// パスワード検索メソッド
		// メールで調べヒットしたパスワードを返す
		public String SearchPass(Staff staff) throws ClassNotFoundException,SQLException {
			
			// 取得するパスワード
			String find_pass = "";
			
			Connection conn = null;
			
			try {	
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
				
				String sql = "SELECT pass FROM staff WHERE mail=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, staff.getMail());
								
				ResultSet rs = pStmt.executeQuery();
							
				// パスワードが取得できれば
				if (rs.next()) {
					find_pass = rs.getString("pass");
				} 
			
			// 必ずDBを切断
			} finally { 
				if (conn != null) {
					conn.close();
				}
			}
			return find_pass;		
		}
	
		
	// 新規登録メソッド
	// レコードを追加できればtrue、できなければfalse
	public boolean canCreateStaff(Staff staff) throws ClassNotFoundException, SQLException {
		
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
	// 同じメール、パスがあるか確認し取得後、Staffインスタンスに格納し返す。
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
	
	// アカウント更新メソッド
	// 上書きしたStaffインスタンスを返す
	public Staff UpDate(Staff staff, String column, String update_value) throws ClassNotFoundException, SQLException {

		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
		    
			// カラム名を変数に
			String sql = "UPDATE staff SET " + column + " = ? WHERE mail = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);					    	
			pStmt.setString(1, update_value);
			pStmt.setString(2, staff.getMail());
			
			int result = pStmt.executeUpdate();

			// もしも更新できなければ
			if (result != 1) {
				System.out.println("更新ができませんでした。");
				return staff;
			
			} else { 
				String sql1 = "SELECT * FROM staff WHERE mail=?";
			    PreparedStatement pStmt1 = conn.prepareStatement(sql1);
			    pStmt1.setString(1, staff.getMail());
			    
			    ResultSet rs = pStmt1.executeQuery();

			    // 変更後のアカウントを取得
			    if (rs.next()) { 
			    	String mail = rs.getString("mail");
			    	String pass = rs.getString("pass");
			    	String name = rs.getString("name");
			    	
			    	staff = new Staff(mail,pass,name);			    	
			    }
			}
			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return staff;
	}
}