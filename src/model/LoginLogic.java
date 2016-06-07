// ログインロジック
package model;

import dao.StaffDAO;

public class LoginLogic {
	
	public boolean execute(Login login) {
		StaffDAO dao = new StaffDAO();
		Staff staff = dao.findByLogin(login);
		return staff != null;
		// trueなら上記のように記述できる
	}
}
