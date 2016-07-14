package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoCreateStaff {
	
	// ランダムアカウントを作成
	public List<Staff> autoCreateStaff(int number) {
		
		Random rnd = new Random();
		
		Staff find_staff = null;
		List<Staff> staff_List = new ArrayList<Staff>();

		// メール、パス
		String pattern_mail_pass = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		// 名前
		String pattern_name = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん";
		
		// x件数回す
		for (int i = 0; i < number; i++) {
			
			StringBuilder m = new StringBuilder();
			StringBuilder p = new StringBuilder();
			StringBuilder n = new StringBuilder();
			
			// メール10文字
			for (int x = 0; x < 10; x++){
				int val = rnd.nextInt(pattern_mail_pass.length());
				m.append(pattern_mail_pass.charAt(val));
			}
		
			m.append("@co.jp");
			
			System.out.println("mail = "+m);
		
			// パスワード6文字
			for (int x = 0; x < 6; x++){
				int val = rnd.nextInt(pattern_mail_pass.length());
				p.append(pattern_mail_pass.charAt(val));
			}
		
			System.out.println("pass = "+p);
		
			// 名前4文字
			for (int x = 0; x < 4; x++){
				int val = rnd.nextInt(pattern_name.length());
				n.append(pattern_name.charAt(val));
			}
				
			System.out.println("name = "+n);
			
			// String型に変換
			String mail = m.toString();
			String pass = p.toString();
			String name = n.toString();
			
			// インスタンスに格納
			find_staff = new Staff(mail, pass, name);		
 				
			// Listにインスタンスを順番に詰める		
			staff_List.add(find_staff);
			
			System.out.println(staff_List.toString());
			
		} // 親for終了
		
		System.out.println("結果"+staff_List.toString());
		
		return staff_List;
	}
}