package hu.zsomboro.rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zsomboro.rev.buisness.PriceAverage;
import hu.zsomboro.rev.dao.PriceAverageDAO;

@Service("priceAverageService")
public class PriceAverageServiceImple implements PriceAverageService {

	@Autowired
	private PriceAverageDAO dao;

	@Override
	public List<PriceAverage> getAllPriceAverages() {
		return dao.getAllPriceAverages();
	}

}
