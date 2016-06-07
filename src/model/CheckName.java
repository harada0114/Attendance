package model;

public class CheckName {
	
	public enum ErrorMsgName {
		OK_NAME, MAX_WORD_NAME, SPACE_NAME, BLANK_NAME,
	}
	
	public ErrorMsgName checkName(String name) {
		
		// 20文字以内
		if (name.length() > 20) {
			return ErrorMsgName.MAX_WORD_NAME;
			
		// スペースが含まれるか
		} else if (name.indexOf(" ") != -1 || name.indexOf("　") != -1) {
			return ErrorMsgName.SPACE_NAME;
	
		// 空文字
		} else if (name.isEmpty()) {
			return ErrorMsgName.BLANK_NAME;
											
		} else {		
			return ErrorMsgName.OK_NAME;
		}
	}
}