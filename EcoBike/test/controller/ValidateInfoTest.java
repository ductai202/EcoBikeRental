package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateInfoTest {
	private BikeController bikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		bikeController = new BikeController();
	}

	@ParameterizedTest
	@CsvSource({
		"xe d@p, 3, BK123,11/11/2021,Yamaha,10000,false",
		"xe dap, 0, BK123,11/11/2021,Yamaha,10000,false",
		"xe dap, 3, BK@123,11/11/2021,Yamaha,10000,false",
		"xe dap, 3, BK123,31/11/2021,Yamaha,10000,false",
		"xe dap, 3, BK123,11/11/2021,Y@m@ha,10000,false",
		"xe dap, 3, BK123,11/11/2021,Yamaha,,false",
		"xe dap, 3, BK123,11/11/2021,Yamaha,10000,true"
	})
			
	void test(String name, String weight, String bikeCode, String dateSX, String producer, String value, boolean expected) {
		HashMap<String, String> info = new HashMap<>();
		info.put("name", name);
		info.put("weight", weight);
		info.put("value", value);
		info.put("bikeCode", bikeCode);
		info.put("dateSX", dateSX);
		info.put("producer", producer);
		boolean isValid = bikeController.validateInfo(info);
		assertEquals(expected, isValid);
	}
}
