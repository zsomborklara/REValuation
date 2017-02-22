package hu.zsomboro.rev.service;

import java.util.List;

import hu.zsomboro.rev.buisness.RealEstate;

public interface RealEstateService {

	List<RealEstate> getAllRealEstates();

	void saveRealEstate(RealEstate re);
}
