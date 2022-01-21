package controller;

import dao.StationDAO;
import daoimpl.StationDAOImpl;
import entity.Station;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StationController extends BaseController{
    private StationDAO stationDAO;

    public StationController() {
        stationDAO = new StationDAOImpl();
    }
    public boolean updateStation(Station station) throws SQLException {
        return stationDAO.update(station);
    }

    public Station insertStation(Station station) throws SQLException {
        return stationDAO.insert(station);
    }

    public boolean validateUpdateInfo(HashMap<String, String> info) {
        if (!validateAddress(info.get("stationAddress"))) {
            return false;
        }
        if (!validateName(info.get("stationName"))) {
            return false;
        }
        if (!validateTotalParking(info.get("totalParking"))) {
            return false;
        }
        return true;
    }

    public boolean validateInsertInfo(HashMap<String, String> info) {
        if (!validateAddress(info.get("stationAddress"))) {
            return false;
        }
        if (!validateName(info.get("stationName"))) {
            return false;
        }
        if (!validateTotalParking(info.get("totalParking"))) {
            return false;
        }
        return true;
    }

    public boolean validateAddress(String address) {
        if (address == null) return false;
        if (address.length() == 0) return false;
        if (address.length() > 45) return false;
        String regx = "[^a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9 ]";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(address);
        return !matcher.find();
    }

    public boolean validateName(String name) {
        if (name == null) return false;
        if (name.length() == 0) return false;
        if (name.length() > 45) return false;
        String regx = "[^a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9 ]";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }

    public boolean validateTotalParking(String number) {
        if (number == null) return false;
        if (number.length() == 0) return false;
        if (number.startsWith("0")) return false;
        if (number.length() > 10) return false;
        String regx = "[^0-9]";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(number);
        return !matcher.find();
    }
}
