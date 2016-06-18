package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class LoginLogic {
	
	public Staff execute(Login login) throws ClassNotFoundException,SQLException{
		StaffDAO dao = new StaffDAO();
		return dao.findByLogin(login);
	}
}
