package model;

import java.sql.SQLException;
import java.util.List;

import dao.StaffDAO;

public class GetUserListLogic {
	
	public List<Staff> execute() throws ClassNotFoundException, SQLException {
		
		StaffDAO dao = new StaffDAO();		
		return dao.findAll();
	}
}
