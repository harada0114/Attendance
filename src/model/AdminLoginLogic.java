package model;

import java.sql.SQLException;

import dao.AdminDAO;

public class AdminLoginLogic {
	
	public boolean isexecute(String pass) throws ClassNotFoundException,SQLException {
		
		AdminDAO dao = new AdminDAO();
		return dao.isPass(pass);
		
	}
}
