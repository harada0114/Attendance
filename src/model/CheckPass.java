package model;

import java.util.regex.Pattern;

public class CheckPass {
	
	public enum ErrorMsgPass {
		OK_PASS, MAX_WORD_PASS, BLANK_PASS,
	}
	
	public ErrorMsgPass checkPass(String pass) {
		
		Pattern pattern_pass = Pattern.compile("^[0-9a-zA-Z]{6}$"); // pass(半角英数6文字)
		
		// 空文字
		if (pass.isEmpty()) {
			return ErrorMsgPass.BLANK_PASS;

		// 半角英数6文字
		} else if (pattern_pass.matcher(pass).matches() != true) {
			return ErrorMsgPass.MAX_WORD_PASS;
								
		} else {		
			return ErrorMsgPass.OK_PASS;
		}
	}
}