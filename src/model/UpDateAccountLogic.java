package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class UpDateAccountLogic {
	
	// アカウント設定変更ロジック
	public Staff execute (Staff staff, String column,String update_value) throws ClassNotFoundException, SQLException {
		
		StaffDAO s = new StaffDAO();		
		return s.UpDate(staff, column, update_value);
	}
}
