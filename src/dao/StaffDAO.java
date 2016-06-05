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
	
	// 重複メールアドレスチェック
	public boolean doubleMail(String mail) throws ClassNotFoundException,SQLException {
			
		Connection conn = null;		
		Class.forName("com.mysql.jdbc.Driver");	
		conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
			
		String sql = "SELECT mail FROM staff WHERE mail=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, mail);
							
		ResultSet rs = pStmt.executeQuery();
						
		// 同じメールアドレスが取得できれば
		if (rs.next()) {
			return true;
		}
		if (conn != null) {
			conn.close();
		}
		return false;		
	}
		
		
	// 新規登録
	public boolean createStaff(Staff staff) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
		    	
		String sql = "INSERT INTO staff(mail,pass,name) VALUES(?,?,?)";
	    PreparedStatement pStmt = conn.prepareStatement(sql);
	    pStmt.setString(1, staff.getMail());
	    pStmt.setString(2, staff.getPass());
	    pStmt.setString(3, staff.getName());
		
	    int result = pStmt.executeUpdate();
				
	    if (result != 1) {
	    	return false;
		}
	    
		if (conn != null) {
			conn.close();
		}
		return true;
	}
		
		
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
}