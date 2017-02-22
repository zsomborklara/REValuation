package hu.zsomboro.rev.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.zsomboro.rev.REVIntegrationTest;
import hu.zsomboro.rev.buisness.PriceAverage;

public class PriceAverageServiceTest extends REVIntegrationTest {

	@Autowired
	private PriceAverageService srv;

	@Test
	public void canLoadPriceAverages(){
		List<PriceAverage> averages = srv.getAllPriceAverages();
		assertThat(averages, not(emptyIterable()));
	}

}
