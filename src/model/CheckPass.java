package model;

import java.util.regex.Pattern;

public class CheckPass {
	
	public enum ErrorMsgPass {
		OK, MAX_WORD, BLANK,
	}
	
	public ErrorMsgPass checkPass(String pass) {
		
		Pattern pattern_pass = Pattern.compile("^[0-9a-zA-Z]{6}$"); // pass(半角英数6文字)
		
		// 空文字
		if (pass.isEmpty()) {
			return ErrorMsgPass.BLANK;

		// 半角英数6文字
		} else if (pattern_pass.matcher(pass).matches() != true) {
			return ErrorMsgPass.MAX_WORD;
								
		} else {		
			return ErrorMsgPass.OK;
		}
	}
}