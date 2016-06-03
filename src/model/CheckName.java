package model;

public class CheckName {
	
	public enum ErrorMsgName {
		name_ok,name_limit,name_space,name_blank,
	}
	
	public ErrorMsgName checkName(String name) {
		
		// 20文字以内
		if (name.length() >= 21) {
			return ErrorMsgName.name_limit;
			
		// スペースが含まれるか
		} else if (name.indexOf(" ") != -1 || name.indexOf("　") != -1) {
			return ErrorMsgName.name_space;
	
		// 空文字
		} else if (name.isEmpty()) {
			return ErrorMsgName.name_blank;
											
		} else {		
			return ErrorMsgName.name_ok;
		}
	}
}