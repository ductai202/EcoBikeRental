package dao;

import java.util.List;

import entity.Bike;

public interface BikeDAO extends BaseDAO<Bike> {
	public List getBikesByStationId(int stationId);
	public boolean  updateRentBikeById(int bikeId);
	public boolean  updateReturnBikeById(int bikeId, int stationId);
	public Bike getByBikeCode(String bikeCode) ;
}
