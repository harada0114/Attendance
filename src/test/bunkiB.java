// 呼び出し先(メソッドを作った側)

package test;

public class bunkiB {
	
	// 出勤か退勤かの分岐スイッチ
	public static boolean on(String a) {
					
		if (a.equals("admission")) {	
			return true;
		} else {
			return false;
		}
	}
	
	// static をつける事によって共有できる
	public static boolean a = on("admission");
	public static boolean b = on("leaving");

}
