package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class CreateStaffLogic {
	
	public boolean execute(Staff staff) throws ClassNotFoundException, SQLException {
	
			StaffDAO dao = new StaffDAO();
			return dao.canCreateStaff(staff);
	}
}