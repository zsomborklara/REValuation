package hu.zsomboro.rev.dao;

import java.util.List;

import hu.zsomboro.rev.buisness.RealEstate;

public interface RealEstateDAO {

	List<RealEstate> getAllRealEstates();

	void saveRealEstate(RealEstate re);
}
