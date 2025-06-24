package dto;

import java.io.Serializable;

public class RequestJoin implements Serializable{
	private int request_id;
	private int id;
	private double current_latitude;
	private double current_longitude;
	private double drop_off_latitude;
	private double drop_off_longitude;
	private int headcount;
	private int status;
	private int partner_id;
	private int talking;
	private int smoking;
	private int partner_gender;
	private String registration_date;
	private String desired_date;
	private int stand_by_id;
	
	//userとの結合
	private String nickname;
	private int gender;
	
	//StandByUserとの結合
	private double prtnr_current_latitude;
	private double prtnr_current_longitude;
	private double prtnr_drop_off_latitude;
	private double prtnr_drop_off_longitude;
	
	public RequestJoin(int request_id, int id, double current_latitude, double current_longitude,
			double drop_off_latitude, double drop_off_longitude, int headcount, int status, int partner_id, int talking,
			int smoking, int partner_gender, String registration_date, String desired_date, int stand_by_id,
			String nickname, int gender, double prtnr_current_latitude, double prtnr_current_longitude,
			double prtnr_drop_off_latitude, double prtnr_drop_off_longitude) {
		super();
		this.request_id = request_id;
		this.id = id;
		this.current_latitude = current_latitude;
		this.current_longitude = current_longitude;
		this.drop_off_latitude = drop_off_latitude;
		this.drop_off_longitude = drop_off_longitude;
		this.headcount = headcount;
		this.status = status;
		this.partner_id = partner_id;
		this.talking = talking;
		this.smoking = smoking;
		this.partner_gender = partner_gender;
		this.registration_date = registration_date;
		this.desired_date = desired_date;
		this.stand_by_id = stand_by_id;
		this.nickname = nickname;
		this.gender = gender;
		this.prtnr_current_latitude = prtnr_current_latitude;
		this.prtnr_current_longitude = prtnr_current_longitude;
		this.prtnr_drop_off_latitude = prtnr_drop_off_latitude;
		this.prtnr_drop_off_longitude = prtnr_drop_off_longitude;
	}
	public RequestJoin() {
		super();
		this.request_id = 0;
		this.id = 0;
		this.current_latitude = 0;
		this.current_longitude = 0;
		this.drop_off_latitude = 0;
		this.drop_off_longitude = 0;
		this.headcount = 0;
		this.status = 0;
		this.partner_id = 0;
		this.talking = 0;
		this.smoking = 0;
		this.partner_gender = 0;
		this.registration_date = "";
		this.desired_date = "";
		this.stand_by_id = 0;
		this.nickname = "";
		this.gender = 0;
		this.prtnr_current_latitude = 0;
		this.prtnr_current_longitude = 0;
		this.prtnr_drop_off_latitude = 0;
		this.prtnr_drop_off_longitude = 0;
	}

	public String getDesired_date() {
		return desired_date;
	}
	public void setDesired_date(String desired_date) {
		this.desired_date = desired_date;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCurrent_latitude() {
		return current_latitude;
	}
	public void setCurrent_latitude(double current_latitude) {
		this.current_latitude = current_latitude;
	}
	public double getCurrent_longitude() {
		return current_longitude;
	}
	public void setCurrent_longitude(double current_longitude) {
		this.current_longitude = current_longitude;
	}
	public double getDrop_off_latitude() {
		return drop_off_latitude;
	}
	public void setDrop_off_latitude(double drop_off_latitude) {
		this.drop_off_latitude = drop_off_latitude;
	}
	public double getDrop_off_longitude() {
		return drop_off_longitude;
	}
	public void setDrop_off_longitude(double drop_off_longitude) {
		this.drop_off_longitude = drop_off_longitude;
	}
	public int getHeadcount() {
		return headcount;
	}
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}
	public int getTalking() {
		return talking;
	}
	public void setTalking(int talking) {
		this.talking = talking;
	}
	public int getSmoking() {
		return smoking;
	}
	public void setSmoking(int smoking) {
		this.smoking = smoking;
	}
	public int getPartner_gender() {
		return partner_gender;
	}
	public void setPartner_gender(int partner_gender) {
		this.partner_gender = partner_gender;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public int getStand_by_id() {
		// TODO 自動生成されたメソッド・スタブ
		return stand_by_id;
	}
	public void setStand_by_id(int stand_by_id) {
		// TODO 自動生成されたメソッド・スタブ
		this.stand_by_id = stand_by_id;
	}
	
	//User
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	//StandByUser
	public double getPrtnr_current_latitude() {
		return prtnr_current_latitude;
	}
	public void setPrtnr_current_latitude(double prtnr_current_latitude) {
		this.prtnr_current_latitude = prtnr_current_latitude;
	}
	public double getPrtnr_current_longitude() {
		return prtnr_current_longitude;
	}
	public void setPrtnr_current_longitude(double prtnr_current_longitude) {
		this.prtnr_current_longitude = prtnr_current_longitude;
	}
	public double getPrtnr_drop_off_latitude() {
		return prtnr_drop_off_latitude;
	}
	public void setPrtnr_drop_off_latitude(double prtnr_drop_off_latitude) {
		this.prtnr_drop_off_latitude = prtnr_drop_off_latitude;
	}
	public double getPrtnr_drop_off_longitude() {
		return prtnr_drop_off_longitude;
	}
	public void setPrtnr_drop_off_longitude(double prtnr_drop_off_longitude) {
		this.prtnr_drop_off_longitude = prtnr_drop_off_longitude;
	}
}
