package model;

import java.util.regex.Pattern;

public class CheckPass {
	
	public enum ErrorMsgPass {
		pass_ok,pass_limit,pass_blank,
	}
	
	public ErrorMsgPass checkPass(String pass) {
		
		Pattern pattern_pass = Pattern.compile("^[0-9a-zA-Z]{6}$"); // pass(半角英数6文字)
		
		// 空文字
		if (pass.isEmpty()) {
			return ErrorMsgPass.pass_blank;

		// 半角英数6文字
		} else if (pattern_pass.matcher(pass).matches() != true) {
			return ErrorMsgPass.pass_limit;
								
		} else {		
			return ErrorMsgPass.pass_ok;
		}
	}
}