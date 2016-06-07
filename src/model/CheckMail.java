package model;

import java.sql.SQLException;

public class CheckMail {
	    
	public enum ErrorMsgMail {
		OK, MAX_WORD, BLANK, NG_PATTERN, DUPLICATE,
	}
	
	public ErrorMsgMail checkMail(String mail) throws ClassNotFoundException, SQLException {
		
		String pattern_mail = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$"; // メールアドレスの形式
			
		CheckDoubleMailLogic logic = new CheckDoubleMailLogic();
		boolean double_mail = logic.execute(mail);
			
		// 40文字以内
		if (mail.length() > 40) {
			return ErrorMsgMail.MAX_WORD;
			
		// 空文字
		} else if (mail.isEmpty()) {			
			return ErrorMsgMail.BLANK;

		// メールアドレス形式に一致しないなら
		} else if (!mail.matches(pattern_mail)) {
			return ErrorMsgMail.NG_PATTERN;
				
		// メールアドレスの重複禁止
		} else if (double_mail) {
			return ErrorMsgMail.DUPLICATE;
		}
			
		return ErrorMsgMail.OK; // チェックOK
	}	
}