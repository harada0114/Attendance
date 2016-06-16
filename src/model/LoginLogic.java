// ログイン
package model;

import java.sql.SQLException;

import dao.StaffDAO;

public class LoginLogic {
	
	public Staff execute(Login login) throws ClassNotFoundException,SQLException{
		StaffDAO dao = new StaffDAO();
		Staff staff = dao.findByLogin(login);
		return staff;
		
		//return staff != null;
		// trueなら上記のように記述できる
	}
}
