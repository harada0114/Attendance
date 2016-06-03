// 呼び出し元(使いたい側、呼び出し先で設定された値を使いたい側)

package test;

import test.bunkiB;

public class bunki {

	public static void main(String[] args) {
		
		boolean c = bunkiB.a;
		System.out.println(c);
		
		boolean a = bunkiB.on("admission");
		System.out.println(a);

	}

}
