package test;

import model.Staff;
import model.Login;
import dao.StaffDAO;

public class StaffDAOTest {
	
	public static void main(String[] args) {
		testFindByLogin1(); // ユーザが見つかる場合のテスト
		testFindByLogin2(); // ユーザが見つからない場合のテスト
	}
	
	public static void testFindByLogin1() {
		// mailでharada@dandt.co.jp、passで2929を入力
	    Login login = new Login("harada@dandt.co.jp","2929");
	    StaffDAO dao = new StaffDAO();
	    Staff result = dao.findByLogin(login);
	    
	    // もし、例外処理が起こらず以下と同じなら成功
	    if (result != null &&
	    	result.getMail().equals("harada@dandt.co.jp") &&
	        result.getPass().equals("2929")) {
	    		System.out.println("findByLogin1:成功しました");
	    } else {
	      System.out.println("findByLogin1:失敗しました");
	    }
	  }
	
	public static void testFindByLogin2() {
		// 該当しないスタッフを入力
		Login login = new Login("harada@dandt.co.jp","29292");
	    StaffDAO dao = new StaffDAO();
	    Staff result = dao.findByLogin(login);
	    
	    if (result == null) {
	      System.out.println("findByLogin2:成功しました");
	    
	    } else {
	      System.out.println("findByLogin2:失敗しました");
	    }
	  }
}
