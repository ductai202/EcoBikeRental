package entity;

import java.io.File;

import javafx.scene.image.Image;
import utils.Configs;

public class Bike {

	/**
	 * id được tạo tăng đần
	 */
	private int bikeId;

	/**
	 * tên của xe
	 */
	private String name;

	/**
	 * giá trị của xe
	 */
	private int value;

	/**
	 * trọng lượng của xe
	 */
	private float weight;

	/**
	 * mã xe
	 */
	private String bikeCode;

	/**
	 * nơi xe đang được đặt
	 */
	private int stationId;

	private boolean isInUse = false;

	/**
	 * loại xe (1 trong 3 loại xe)
	 */
	private String type;
	/**
	 * nha san xuat
	 */
	private String producer;

	/**
	 * ngay san xuat
	 */
	private String dateSX;

	public Bike() {

	}

	public Bike(String type) {
		super();
		this.type = type;
	}

	public Bike(int bikeId, String name, int value, float weight, String bikeCode, int stationId, boolean isInUse,
			String type, String dateSX, String producer) {
		super();
		this.bikeId = bikeId;
		this.name = name;
		this.value = value;
		this.weight = weight;
		this.bikeCode = bikeCode;
		this.stationId = stationId;
		this.isInUse = isInUse;
		this.type = type;
		this.dateSX = dateSX;
		this.producer = producer;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDateSX() {
		return dateSX;
	}

	public void setDateSX(String dateSX) {
		this.dateSX = dateSX;
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getBikeCode() {
		return bikeCode;
	}

	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public boolean isInUse() {
		return isInUse;
	}

	public void setInUse(boolean isInUse) {
		this.isInUse = isInUse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeBike() {
		if (this.getType().equals(Configs.TYPE_BIKE)) {
			return "Xe đạp thường";
		} else if (this.getType().equals(Configs.TYPE_EBIKE)) {
			return "Xe đạp điện";
		} else if (this.getType().equals(Configs.TYPE_TWIN_BIKE)) {
			return "Xe đạp đôi";
		}
		return "";
	}

	public String mappingTypeBikeToType(String xeDap) {
		if (xeDap.equals("Xe đạp thường")) {
			return Configs.TYPE_BIKE;
		} else if (xeDap.equals("Xe đạp điện")) {
			return Configs.TYPE_EBIKE;
		} else {
			return Configs.TYPE_TWIN_BIKE;
		}
	}

	public Image getImageBike() {
		Image image = null;
		if (this.getType().equals("b")) {
			File file = new File(Configs.URL_BIKE);
			image = new Image(file.toURI().toString());

		} else if (this.getType().equals("eb")) {
			File file = new File(Configs.URL_EBIKE);
			image = new Image(file.toURI().toString());
		} else if (this.getType().equals("tb")) {
			File file = new File(Configs.URL_TWIN_BIKE);
			image = new Image(file.toURI().toString());
		}
		return image;
	}
}
