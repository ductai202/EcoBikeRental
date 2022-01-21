package calculator_bike;

import entity.Bike;
import utils.Configs;

public class CurrentCalculatorBikeDeposit implements CalculatorBikeDeposit {

	@Override
	public int calculateBikeDeposit(String typeBike) {
		int rs = -1;
		if (typeBike.equals(Configs.TYPE_BIKE)) {
			rs = 400000;
		} else if (typeBike.equals(Configs.TYPE_EBIKE)) {
			rs = 700000;
		} else if (typeBike.equals(Configs.TYPE_TWIN_BIKE)) {
			rs = 550000;
		}
		return rs;
	}

}
