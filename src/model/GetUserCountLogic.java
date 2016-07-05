package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class GetUserCountLogic {
	
	public int execute() throws ClassNotFoundException, SQLException {
		
		StaffDAO dao = new StaffDAO();
		int count = dao.staffCount();
		
		// ページ数を返す
		// 1ページに10件表示したい
		int page = count / 10;
		
		// あまった分を次へに表示するため
		if (count % 10 != 0) {
			page = page + 1;
		}
		
		return page;
		
	}

}
