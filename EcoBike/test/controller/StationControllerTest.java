package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StationControllerTest {

    private StationController stationController;

    @BeforeEach
    void setUp() throws Exception {
        stationController = new StationController();
    }

    @ParameterizedTest
    @CsvSource({
            "Bai xe vip,true",
            "Bãi xe vip,true",
            "Bãi xe 01,true",
            "a,true",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,true",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,false",
            ",false",
            "Bãi xe V!p, false"

    })
    void validateName(String name, boolean expected) {
        boolean isValided = stationController.validateName(name);
        assertEquals(expected, isValided);
    }

    @ParameterizedTest
    @CsvSource({
            "Tay Ho Viet Nam,true",
            "Đại Cồ Việt,true",
            "Số 1 Đại Cồ Việt,true",
            "a,true",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,true",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,false",
            ",false",
            "Đ@i Cô` Việt, false"
    })
    void validateAddress(String name, boolean expected) {
        boolean isValided = stationController.validateAddress(name);
        assertEquals(expected, isValided);
    }

    @ParameterizedTest
    @CsvSource({
            "1231,true",
            "1,true",
            "1111111111,true",
            "11111111111,false",
            ",false",
            "12a3,false"
    })
    void validateTotalParking(String name, boolean expected) {
        boolean isValided = stationController.validateTotalParking(name);
        assertEquals(expected, isValided);
    }

    @ParameterizedTest
    @CsvSource({
            "B@i xe,Đại Cồ Việt,5,false",
            "Bãi xe,Đ@i Cồ Việt,5,false",
            "Bãi xe,Đại Cồ Việt,0,false",
            "Bãi xe,Đại Cồ Việt,10,true"
    })
    void validateUpdateInfo(String name, String address, String totalParking, boolean expected) {
        HashMap<String, String> info = new HashMap<>();
        info.put("stationAddress", name);
        info.put("stationName", address);
        info.put("totalParking", totalParking);
        boolean isValided = stationController.validateUpdateInfo(info);
        assertEquals(expected, isValided);
    }
}