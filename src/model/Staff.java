// Staff.java(model)
package model;

import java.io.Serializable;

public class Staff implements Serializable {
	
	private String mail; // メールアドレス
	private String pass; // パスワード
	private String name; // 名前
	
	public Staff() {}
	
	public Staff(String mail,String pass,String name) {
		this.mail = mail;
		this.pass = pass;
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
