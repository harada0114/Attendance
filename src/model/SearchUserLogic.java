package model;

import java.sql.SQLException;
import java.util.List;

import dao.StaffDAO;

public class SearchUserLogic {
	
	public List<Staff> execute(String[] word) throws ClassNotFoundException, SQLException{
		
		StaffDAO c = new StaffDAO();
		return c.searchResults(word);	
	}
}
