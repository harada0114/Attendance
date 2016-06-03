// Login.java(model)
package model;

import java.io.Serializable;

public class Login implements Serializable {
	
	private String mail; // メールアドレス
	private String pass; // パスワード
	private String name; // 名前
	
	public Login() {}
	
	public Login(String mail, String pass) {
		this.mail = mail;
		this.pass = pass;

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
		this.pass = name;
	}
}
