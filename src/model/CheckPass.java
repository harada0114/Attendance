package model;

import java.util.regex.Pattern;

public class CheckPass {
	
	public enum ErrorMsgPass {
		ok_pass,limit_pass,blank_pass,
	}
	
	public ErrorMsgPass checkPass(String pass) {
		
		Pattern pattern_pass = Pattern.compile("^[0-9a-zA-Z]{6}$"); // pass(半角英数6文字)
		
		// 空文字
		if (pass.isEmpty()) {
			return ErrorMsgPass.blank_pass;

		// 半角英数6文字
		} else if (pattern_pass.matcher(pass).matches() != true) {
			return ErrorMsgPass.limit_pass;
								
		} else {		
			return ErrorMsgPass.ok_pass;
		}
	}
}