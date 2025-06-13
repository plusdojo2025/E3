package dto;

import java.io.Serializable;

public class IdPw implements Serializable {
	private int id; //ID
	private String pass; // パスワード
	private String email; // メールアドレス
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public IdPw(int id, String email, String pass) {
		this.id = id;
		this.email = email;
		this.pass = pass;
	}
	public IdPw() {
		this.id = 0;
		this.email = "";
		this.pass = "";
	}
}
