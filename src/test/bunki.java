// 呼び出し元(使いたい側、呼び出し先で設定された値を使いたい側)

package test;

import test.bunkiB;
import java.util.UUID;

public class bunki {

	public static void main(String[] args) {
		
		boolean c = bunkiB.a;
		System.out.println(c);
		
		boolean a = bunkiB.on("admission");
		System.out.println(a);
		
		
		// UUIDクラスを使ったランダム。
		// https://docs.oracle.com/javase/jp/6/api/java/util/UUID.html
		
		UUID u2 = UUID.randomUUID();
		String e = u2.toString();
		
		System.out.println(e);
		
		// 1行でも書ける
		String u1 = UUID.randomUUID().toString();
		
		System.out.println(u1);

	}
	
	
	public void a() {
		
	// UUID u2 = UUID.randomUUID();
	// String a = u2.toString();
	
	// System.out.println(a);
		
	// String u1 = UUID.randomUUID().toString();
		
	// Object u1 = UUID.randomUUID();
	// u1.toString();
	
	// }
	
	// APIの見方。randomUUID()の左にあるstatic UUIDはUUID型で返す必要あり↓のようなこと。
	// static public UUID randomUUID() {
		
	//}
}
}
