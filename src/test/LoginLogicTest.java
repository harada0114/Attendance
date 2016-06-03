package test;

import model.Login;
import model.LoginLogic;

public class LoginLogicTest {

	public static void main(String[] args) {
		testExecute1(); // ログイン成功のテスト
	    testExecute2(); // ログイン失敗のテスト
	}

	  public static void testExecute1() {
	    Login login = new Login("harada@dandt.co.jp","2929");
	    LoginLogic bo = new LoginLogic();
	    boolean result = bo.execute(login);
	    
	    if (result) {
	      System.out.println("testExcecute1:成功しました");
	    } else {
	      System.out.println("testExcecute1:失敗しました");
	    }
	  }

	  public static void testExecute2() {
	    Login login = new Login("harada@dandt.co.jp","29292");
	    LoginLogic bo = new LoginLogic();
	    boolean result = bo.execute(login);
	    
	    if (!result) {
	      System.out.println("testExcecute2:成功しました");
	    } else {
	      System.out.println("testExcecute2:失敗しました");
	    }
	 }
}