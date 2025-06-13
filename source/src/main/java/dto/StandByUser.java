package dto;

import java.io.Serializable;

public class StandByUser implements Serializable {
	private int stand_by_id;
	private int id;
	private String date;
	private double current_latitude;
	private double current_longitude;
	private double drop_off_latitude;
	private double drop_off_longitude;
	private int headcount;
	private int flag;
	private String registration_date;
	private int talking;
	private int smoking;
	private int partner_gender;
	
	public StandByUser(int stand_by_id, int id, String date, double current_latitude, double current_longitude,
			double drop_off_latitude, double drop_off_longitude, int headcount, int flag, String registration_date,
			int talking, int smoking, int partner_gender) {
		this.stand_by_id = stand_by_id;
		this.id = id;
		this.date = date;
		this.current_latitude = current_latitude;
		this.current_longitude = current_longitude;
		this.drop_off_latitude = drop_off_latitude;
		this.drop_off_longitude = drop_off_longitude;
		this.headcount = headcount;
		this.flag = flag;
		this.registration_date = registration_date;
		this.talking = talking;
		this.smoking = smoking;
		this.partner_gender = partner_gender;
	}
	public StandByUser() {
		this.stand_by_id = 0;
		this.id = 0;
		this.date = "";
		this.current_latitude = 0;
		this.current_longitude = 0;
		this.drop_off_latitude = 0;
		this.drop_off_longitude = 0;
		this.headcount = 0;
		this.flag = 0;
		this.registration_date = "";
		this.talking = 0;
		this.smoking = 0;
		this.partner_gender = 0;
	}
	
	public int getStand_by_id() {
		return stand_by_id;
	}
	public void setStand_by_id(int stand_by_id) {
		this.stand_by_id = stand_by_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
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
}
