package model;

import java.sql.SQLException;
import java.util.List;

import dao.StaffDAO;

public class SearchMailLogic {
	
	public List<Staff> execute(String[] mail) throws ClassNotFoundException,SQLException {
	
	StaffDAO a = new StaffDAO();
	return a.searchMail(mail);
	
	}
}
