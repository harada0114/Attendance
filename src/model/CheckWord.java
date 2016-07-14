package model;

public class CheckWord {

	// 空白をチェックし、分割した文字列を配列で返す
	public String[] execute(String text) {
		
		String[] b = null;
		
		// 空文字なら
		if (text.isEmpty() || text.indexOf("　") == 0 || text.indexOf(" ") == 0) {
			return b;
		}
		
		// 空白をすべて半角の空白に
		String a = text.replaceAll("[　]", " ");
		
		// 前後の空白を削除
		a.trim();
		
		// 文字列を切り出す
		b = a.split(" ", 0);
		
		return b;
	}	
}