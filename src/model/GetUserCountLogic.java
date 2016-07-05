package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class GetUserCountLogic {
	
	public int execute() throws ClassNotFoundException, SQLException {
		
		StaffDAO dao = new StaffDAO();
		return dao.staffCount();	
	}
}
