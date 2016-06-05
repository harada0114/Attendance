package model;

import java.sql.SQLException;

public class CheckMail {
	    
	public enum ErrorMsgMail {
		ok_mail,limit_mail,blank_mail,pattern_mail,double_mail,
	}
	
	public ErrorMsgMail checkMail(String mail) throws ClassNotFoundException, SQLException {
		
		String pattern_mail = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$"; // メールアドレスの形式
			
		CheckDoubleMailLogic logic = new CheckDoubleMailLogic();
		boolean double_mail = logic.execute(mail);
			
		// 40文字以内
		if (mail.length() >= 41) {
			return ErrorMsgMail.limit_mail;
			
		// 空文字
		} else if (mail.isEmpty()) {			
			return ErrorMsgMail.blank_mail;

		// メールアドレス形式に一致しないなら
		} else if (!mail.matches(pattern_mail)) {
			return ErrorMsgMail.pattern_mail;
				
		// メールアドレスの重複禁止
		} else if (double_mail) {
			return ErrorMsgMail.double_mail;
		}
			
		return ErrorMsgMail.ok_mail; // チェックOK
	}	
}