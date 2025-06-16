package dto;

import java.io.Serializable;

public class ResultUserID implements Serializable {
	private int id;
	private boolean result;
	
	public ResultUserID(int id, boolean result) {
		super();
		this.id = id;
		this.result = result;
	}
	
	public ResultUserID() {
		super();
		this.id = 0;
		this.result = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
}
