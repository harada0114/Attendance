package model;

public class CheckWord {

	// 空白をチェックし、分割した文字列を配列で返す
		public String[] execute(String text) {
			
			String[] b = null;
			
			// 空文字なら
			if (text.isEmpty()) {
				return b;
			}
			
			// 大文字空白をすべて半角空白に
			String a = text.replaceAll("　", " ");
			
			// 前後の空白を削除
			// ※Javaの文字列は不変。元の文字列を変更せずに、両端の空白を削除した新しい文字列を返す。
			String c = a.trim();
			
			// 文字列を切り出す
			b = c.split(" ", 0);
			
			return b;
		}	
	}