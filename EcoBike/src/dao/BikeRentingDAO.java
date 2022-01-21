package dao;

import entity.BikeRentingObject;

public interface BikeRentingDAO extends BaseDAO<BikeRentingObject> {
			
	public BikeRentingObject getTransactionByUserId(int id);
}
