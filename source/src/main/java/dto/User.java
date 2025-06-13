package dto;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private String nickname;
	private int gender;
	private double address_latitude;
	private double address_longitude;
	private int talking;
	private int smoking;
	private int partner_gender;
	private int id;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public double getAddress_latitude() {
		return address_latitude;
	}
	public void setAddress_latitude(double address_latitude) {
		this.address_latitude = address_latitude;
	}
	public double getAddress_longitude() {
		return address_longitude;
	}
	public void setAddress_longitude(double address_longitude) {
		this.address_longitude = address_longitude;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User(String name, String nickname, int gender, double address_latitude, double address_longitude, int talking, int smoking, int partner_gender, int id, String address) {
		this.name = name;
		this.nickname = nickname;
		this.gender = gender;
		this.address_latitude = address_latitude;
		this.address_longitude = address_longitude;
		this.talking = talking;
		this.smoking = smoking;
		this.partner_gender = partner_gender;
		this.id = id;
		this.address = address;
	}
	public User() {
		this.name = "";
		this.nickname = "";
		this.gender = 0;
		this.address_latitude = 0;
		this.address_longitude = 0;
		this.talking = 0;
		this.smoking = 0;
		this.partner_gender = 0;
		this.id = 0;
		this.address = "";	
		}

}
