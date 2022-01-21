package dao;

import entity.Station;

public interface StationDAO extends BaseDAO<Station> {
	public Station findByName(String stationName);
}
