// ログインロジック
package model;

import dao.StaffDAO;

public class LoginLogic {
	
	public boolean execute(Login login) {
		StaffDAO dao = new StaffDAO();
		// クラス型staff変数にDAOの結果の一致したユーザの値にstaffフィールドを書き換える
		// →参照渡し
		Staff staff = dao.findByLogin(login);
		return staff != null;
		
		
		//if (staff != null) {
		//	return true;
	//	}
		//return false;
	}
}
