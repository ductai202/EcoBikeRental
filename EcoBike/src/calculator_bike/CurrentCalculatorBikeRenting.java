package calculator_bike;

import entity.Bike;
import utils.Configs;

public class CurrentCalculatorBikeRenting implements CalculatorBikeRenting {

	@Override
	public int calculatorBikeRenting(String typeBike, int minutes) {
		if (typeBike == null)
			return -1;
		if (typeBike.equals(Configs.TYPE_BIKE)) {
			return bikeFees(minutes);
		} else if (typeBike.equals(Configs.TYPE_EBIKE) || typeBike.equals(Configs.TYPE_TWIN_BIKE))
			return eBikeAndTwinBikeFees(minutes);
		return -1;
	}

	private int bikeFees(int minutes) {
		if (minutes <= 10)
			return 0;
		// gia khoi diem cho 30 phut dau
		int rs = 10000;
		return rs + (int) Math.ceil((double) (minutes - 30) / 15) * 3000;
	}

	private int eBikeAndTwinBikeFees(int minutes) {
		if (minutes <= 10)
			return 0;
		// gia khoi diem cho 30 phut dau
		int rs = 15000;
		return rs + (int) ((int) Math.ceil((double) (minutes - 30) / 15) * 3000 * 1.5);
	}
}
