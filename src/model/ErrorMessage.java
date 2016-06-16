package model;

public class ErrorMessage {
	
	public enum errorMsgMail {
		OK, MAX_WORD, BLANK, NG_PATTERN,
	}
	
	public enum errorMsgPass {
		OK, MAX_WORD, BLANK,
	}
	
	public enum errorMsgName {
		OK, MAX_WORD, SPACE, BLANK,
	}
	
	public enum errorMsgAdmission {
		OK, ALREADY, NOT_ADMISSION, SYSTEM_ERROR,
	}

	public enum MsgLeaving {
		OK, ALREADY, NOT_ADMISSION, SYSTEM_ERROR,
	}
}
