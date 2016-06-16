package model;

public class CheckName {
	
	public enum ErrorMsgName {
		OK, MAX_WORD, SPACE, BLANK,
	}
	
	public ErrorMsgName checkName(String name) {
		
		// 20文字以内
		if (name.length() > 20) {
			return ErrorMsgName.MAX_WORD;
			
		// スペースが含まれるか
		} else if (name.indexOf(" ") != -1 || name.indexOf("　") != -1) {
			return ErrorMsgName.SPACE;
	
		// 空文字
		} else if (name.isEmpty()) {
			return ErrorMsgName.BLANK;
											
		} else {		
			return ErrorMsgName.OK;
		}
	}
}