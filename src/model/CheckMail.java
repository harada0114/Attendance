package model;

public class CheckMail {
	    
	public enum ErrorMsgMail {
		mail_ok,mail_limit,mail_blank,mail_pattern,
	}
	
	public ErrorMsgMail checkMail(String mail) {
		
		String pattern_mail = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$"; // メールアドレスの形式
		
		// 40文字以内
		if (mail.length() >= 41) {
			return ErrorMsgMail.mail_limit;
		
		// 空文字
		} else if (mail.isEmpty()) {			
			return ErrorMsgMail.mail_blank;

		// メールアドレス形式に一致しないなら
		} else if (!mail.matches(pattern_mail)) {
			return ErrorMsgMail.mail_pattern;
		
		// チェックOK
		} else {
		return ErrorMsgMail.mail_ok;
		}
	}	
}