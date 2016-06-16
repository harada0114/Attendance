package model;

import java.sql.SQLException;

public class CheckMail {
	    
	public enum ErrorMsgMail {
		OK, MAX_WORD, BLANK, NG_PATTERN,
	}
	
	public ErrorMsgMail checkMail(String mail) {
		
		String pattern_mail = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$"; // メールアドレスの形式
						
		// 40文字以内
		if (mail.length() > 40) {
			return ErrorMsgMail.MAX_WORD;
			
		// 空文字
		} else if (mail.isEmpty()) {			
			return ErrorMsgMail.BLANK;

		// メールアドレス形式に一致しないなら
		} else if (!mail.matches(pattern_mail)) {
			return ErrorMsgMail.NG_PATTERN;
		}
			
		return ErrorMsgMail.OK; // チェックOK
	}	
	
	// メールアドレス重複確認メソッド
	public boolean duplicate(String mail) throws ClassNotFoundException, SQLException {

		CheckDoubleMailLogic logic = new CheckDoubleMailLogic();
		return logic.execute(mail);
	}
}