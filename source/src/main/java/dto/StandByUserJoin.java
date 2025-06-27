package dto;

public class StandByUserJoin {
	 
	 private int id;
	 private int stand_by_id;
	 private String nickname;
	 private int gender;
	 private int headcount;
	 private double current_latitude;
	 private double current_longitude;
	 private double drop_off_latitude;
	 private double drop_off_longitude;
	 private String registration_date;
	 private String date;
	 
	public StandByUserJoin(int id, int stand_by_id, String nickname, int gender, int headcount, double current_latitude,
			double current_longitude, double drop_off_latitude, double drop_off_longitude, String registration_date, String date) {
		this.id = id;
		this.stand_by_id = stand_by_id;
		this.nickname = nickname;
		this.gender = gender;
		this.headcount = headcount;
		this.current_latitude = current_latitude;
		this.current_longitude = current_longitude;
		this.drop_off_latitude = drop_off_latitude;
		this.drop_off_longitude = drop_off_longitude;
		this.registration_date = registration_date;
		this.date = date;
	}
	public StandByUserJoin() {
		this.id = 0;
		this.stand_by_id = 0;
		this.nickname = "";
		this.gender = 0;
		this.headcount = 0;
		this.current_latitude = 0;
		this.current_longitude = 0;
		this.drop_off_latitude = 0;
		this.drop_off_longitude = 0;
		this.registration_date = "";
		this.date = "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStand_by_id() {
		return id;
	}
	public void setStand_by_id(int stand_by_id) {
		this.stand_by_id = stand_by_id;
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
	public int getHeadcount() {
		return headcount;
	}
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
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
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
