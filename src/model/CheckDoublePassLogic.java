package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class CheckDoublePassLogic {

	public String execute(Staff staff) throws ClassNotFoundException,SQLException {
		
		StaffDAO dao = new StaffDAO();
		return dao.SearchPass(staff);
	}
}
