package model;

import java.io.Serializable;

public class Time implements Serializable {
	
	private String mail;       // メールアドレス
	private String day;        // 日付
	private String admission;  // 出社日時
	private String leaving;    // 退社日時
	
	public Time() {}
	
	public Time (String mail,String day,String admission, String leaving) {
		this.mail = mail;
		this.day = day;
		this.admission = admission;
		this.leaving = leaving;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getAdmission() {
		return admission;
	}
	public void setAdmission(String admission) {
		this.admission = admission;
	}
	
	public String getLeaving() {
		return leaving;
	}
	public void setLeaving(String leaving) {
		this.leaving = leaving;
	}
	
}