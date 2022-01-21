package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.regex.Pattern;

import calculator_bike.CalculatorBikeDeposit;
import calculator_bike.CalculatorBikeRenting;
import calculator_bike.CurrentCalculatorBikeDeposit;
import calculator_bike.CurrentCalculatorBikeRenting;

import java.util.regex.Matcher;
import dao.BikeDAO;
import daoimpl.BikeDAOImpl;
import entity.Bike;

public class BikeController extends BaseController {
	BikeDAO bikeDAO;
	CalculatorBikeDeposit calculatorBikeDeposit;
	CalculatorBikeRenting calculatorBikeRenting;

	public BikeController() {
		bikeDAO = new BikeDAOImpl();
	}

	public List getBikesByStationId(int stationId) {
		return bikeDAO.getBikesByStationId(stationId);
	}

	public Bike getBikeByBikeId(int bikeId) throws SQLException {
		return bikeDAO.findById(bikeId);
	}

	public boolean updateBike(Bike bike) throws SQLException {
		return bikeDAO.update(bike);
	}

	public Bike insertBike(Bike bike) throws SQLException {
		return bikeDAO.insert(bike);
	}
	
	public Bike getByBikeCode(String bikeCode) {
		return bikeDAO.getByBikeCode(bikeCode);
	}

	public boolean validateInfo(HashMap<String, String> info) {
		if (!validateName(info.get("name"))) {
			return false;
		}
		if (!validateWeight(info.get("weight"))) {
			return false;
		}
		if (!validateBikeCode(info.get("bikeCode"))) {
			return false;
		}
		if (!validateDate(info.get("dateSX"))) {
			return false;
		}
		if (!validateName(info.get("producer"))) {
			return false;
		}
		if (!validateValue(info.get("value"))) {
			return false;
		}
		
		
		return true;
	}

	public boolean validateBikeCode(String bikeCode) {
		if (bikeCode == null) return false;
		if (bikeCode.length() == 0) return false;
		if (bikeCode.length() > 10) return false;
		String regx = "[^A-Za-z0-9 ]";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(bikeCode);
		return !matcher.find();
	}

	public boolean validateValue(String value) {
		if (value == null) return false;
		if (value.length() == 0) return false;
		if (value.length() > 30) return false;
		if (value.startsWith("0")) return false;
		String regx = "[^0-9]";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(value);
		return !matcher.find();
	}

	public boolean validateWeight(String weight) {
		if (weight == null) return false;
		if (weight.length() == 0) return false;
		if (weight.startsWith("0")) return false;
		String regx = "[^0-9.]";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(weight);
		return !matcher.find();
	}

	public boolean validateDate(String dateString) {
		if (dateString == null) return false;
		if(dateString.length() == 0) return false;
		Pattern dateRegexPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
		Matcher dateMatcher = dateRegexPattern.matcher(dateString);

		if (dateMatcher.matches()) {

			dateMatcher.reset();

			if (dateMatcher.find()) {
				String day = dateMatcher.group(1);
				String month = dateMatcher.group(2);
				int year = Integer.parseInt(dateMatcher.group(3));

				if ("31".equals(day) && ("4".equals(month) || "6".equals(month) || "9".equals(month)
						|| "11".equals(month) || "04".equals(month) || "06".equals(month) || "09".equals(month))) {
					return false; // 1, 3, 5, 7, 8, 10, 12 has 31 days
				} else if ("2".equals(month) || "02".equals(month)) {
					// leap year
					if (year % 4 == 0) {
						return !"30".equals(day) && !"31".equals(day);
					} else {
						return !"29".equals(day) && !"30".equals(day) && !"31".equals(day);
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean validateName(String name) {
		if (name == null) return false;
		if (name.length() == 0) return false;
		if (name.length() > 50) return false;
		String regx = "[^a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9 ]";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(name);
		return !matcher.find();
	}

	public int getBikeDeposit(String typeBike) {
		calculatorBikeDeposit = new CurrentCalculatorBikeDeposit();
		return calculatorBikeDeposit.calculateBikeDeposit(typeBike);
	}

	public int getBikeRenting(String typeBike, int minutes) {
		calculatorBikeRenting = new CurrentCalculatorBikeRenting();
		return calculatorBikeRenting.calculatorBikeRenting(typeBike, minutes);
	}
}
