package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	
public boolean isPass(String pass) throws ClassNotFoundException,SQLException {
		
		Connection conn = null;
		
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/attendance","harada","dandt");
			
			String sql = "SELECT pass FROM admin WHERE pass=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, pass);
							
			ResultSet rs = pStmt.executeQuery();
						
			// 同じpassが取得できれば
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
}
