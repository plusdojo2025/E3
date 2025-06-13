package dto;

public class Taxi {
	private int taxinumber;
	private String company;
	private String phone;
	private double taxi_address_latitude;
	private double taxi_address_longitude;
	private int price;
	private String taxi_address;
	
	// 引数ありコンストラクタ
	public Taxi(int taxinumber, String company, String phone, double taxi_address_latitude,
			double taxi_address_longitude, int price, String taxi_address) {
		this.taxinumber = taxinumber;
		this.company = company;
		this.phone = phone;
		this.taxi_address_latitude = taxi_address_latitude;
		this.taxi_address_longitude = taxi_address_longitude;
		this.price = price;
		this.taxi_address = taxi_address;
	}
	
	// 引数なしコンストラクタ
	public Taxi() {
		this(0, "", "", 0, 0, 0, "");
	}

	// ゲッターとセッター
	public int getTaxinumber() {
		return taxinumber;
	}

	public void setTaxinumber(int taxinumber) {
		this.taxinumber = taxinumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getTaxi_address_latitude() {
		return taxi_address_latitude;
	}

	public void setTaxi_address_latitude(double taxi_address_latitude) {
		this.taxi_address_latitude = taxi_address_latitude;
	}

	public double getTaxi_address_longitude() {
		return taxi_address_longitude;
	}

	public void setTaxi_address_longitude(double taxi_address_longitude) {
		this.taxi_address_longitude = taxi_address_longitude;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTaxi_address() {
		return taxi_address;
	}

	public void setTaxi_address(String taxi_address) {
		this.taxi_address = taxi_address;
	}
}
