package hu.zsomboro.rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zsomboro.rev.buisness.RealEstate;
import hu.zsomboro.rev.dao.RealEstateDAO;

@Service("realEstateServiceImpl")
public class RealEstateServiceImpl implements RealEstateService {

	@Autowired
	private RealEstateDAO dao;

	@Override
	public List<RealEstate> getAllRealEstates() {
		return dao.getAllRealEstates();
	}

	@Override
	public void saveRealEstate(RealEstate re) {
		dao.saveRealEstate(re);
	}

}
