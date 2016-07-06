package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class DeleteUserListLogic {
	
	public boolean execute(String delete_user) throws ClassNotFoundException, SQLException{
		
		StaffDAO a = new StaffDAO();
		return a.canDelete(delete_user);
	}
}
