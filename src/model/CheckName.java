package model;

public class CheckName {
	
	public enum ErrorMsgName {
		ok_name,limit_name,space_name,blank_name,
	}
	
	public ErrorMsgName checkName(String name) {
		
		// 20文字以内
		if (name.length() >= 21) {
			return ErrorMsgName.limit_name;
			
		// スペースが含まれるか
		} else if (name.indexOf(" ") != -1 || name.indexOf("　") != -1) {
			return ErrorMsgName.space_name;
	
		// 空文字
		} else if (name.isEmpty()) {
			return ErrorMsgName.blank_name;
											
		} else {		
			return ErrorMsgName.ok_name;
		}
	}
}