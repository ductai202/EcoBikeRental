package entity;



public class Station {
	
	private int stationId;
	
	/**
	 *	tên bãi xe
	 */
	private String name;
	
	/**
	 *  địa chỉ bãi xe
	 */
	private String address;
	
	private int totalParking;
	
	private int totalBike;
	
	public Station() {
		
	}

	public Station(int stationId, String name, String address, int totalParking, int totalBike) {
		super();
		this.stationId = stationId;
		this.name = name;
		this.address = address;
		this.totalParking = totalParking;
		this.totalBike = totalBike;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTotalParking() {
		return totalParking;
	}

	public void setTotalParking(int totalParking) {
		this.totalParking = totalParking;
	}

	public int getTotalBike() {
		return totalBike;
	}

	public void setTotalBike(int totalBike) {
		this.totalBike = totalBike;
	}

	@Override
	public String toString() {
		return "[" + stationId + "] : " + name + " - " + address + " - Tổng số chỗ trống : "
				+ totalParking + " - Số xe đang có : " + totalBike + "";
	}
	

}
