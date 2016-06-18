package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class CheckDoubleMailLogic {
	
	public boolean execute(String mail) throws ClassNotFoundException,SQLException {
		
		StaffDAO dao = new StaffDAO();
		return dao.isDoubleMail(mail);
	}
}
