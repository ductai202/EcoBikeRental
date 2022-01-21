package entity;

public class UserRentingBikeObject {
	
	private int userId;
	
	private String username;
	
	private String bikeName;
	
	private String bikeCode;
	
	private String type;
	
	private String stationName;
	
	
	
	public UserRentingBikeObject(int userId, String userName, String bikeName, String bikeCode, String type,
			String stationName) {
		super();
		this.userId = userId;
		this.username = userName;
		this.bikeName = bikeName;
		this.bikeCode = bikeCode;
		this.type = type;
		this.stationName = stationName;
	}

	public UserRentingBikeObject() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getBikeCode() {
		return bikeCode;
	}

	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


}
