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

	// ログインチェックメソッド
	public Staff findByLogin(Login login) {
		
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
		    
		    // 取得した結果、一致したユーザが存在した場合 //
		    if (rs.next()) { 
		    	String mail = rs.getString("mail");
		    	String pass = rs.getString("pass");
		    	String name = rs.getString("name");
		    	
		    	staff = new Staff(mail,pass,name);
		    }

		// 例外処理
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}		
		return staff;
	}
	
	// 新規登録メソッド
	public boolean newStaff(Staff staff) {
		Connection conn = null;
		
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");

			// レコードチェック。mailに重複がないか。
			String sql = "SELECT mail, pass, name FROM staff WHERE mail=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, staff.getMail());
					    
		    ResultSet rs = pStmt.executeQuery();
		    
		    // 取得した結果、メールアドレスが一致した場合 //
		    if (rs.next()) { 
		    	String mail = rs.getString("mail");
		    			
		    	if (mail.equals(staff.getMail())) {
		    		return false;
		    	}
		    	
		    } else {
		    	
		    	String sql2 = "INSERT INTO staff(mail,pass,name) VALUES(?,?,?)";
		    	PreparedStatement pStmt2 = conn.prepareStatement(sql2);
		    	pStmt2.setString(1, staff.getMail());
		    	pStmt2.setString(2, staff.getPass());
		    	pStmt2.setString(3, staff.getName());
		
		    	int result = pStmt2.executeUpdate();
						
		    	if (result != 1) {
		    		return false;
		    	}
		    }
		    
			// 例外処理
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
		
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
	
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return true;
	}
}