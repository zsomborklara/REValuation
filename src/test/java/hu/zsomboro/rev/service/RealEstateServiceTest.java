package hu.zsomboro.rev.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Iterables;

import hu.zsomboro.rev.REVIntegrationTest;
import hu.zsomboro.rev.buisness.Coordinate;
import hu.zsomboro.rev.buisness.Coordinate.DecimalDegreeBuilder;
import hu.zsomboro.rev.buisness.RealEstate;

public class RealEstateServiceTest extends REVIntegrationTest {

	@Autowired
	private RealEstateService srv;

	@Test
	public void can_SaveAndLoad_RealEstates() {
		List<RealEstate> emptyList = srv.getAllRealEstates();
		assertThat(emptyList, is(emptyIterable()));

		DecimalDegreeBuilder builder = Coordinate.decimalDegreeBuilder()
				.withLatitudeDegree(100.44)
				.withLongitudeDegree(-33.10);
		double area = 55.0;
		srv.saveRealEstate(new RealEstate(builder.build(), area, 4000, 0));

		RealEstate loadedModel = Iterables.getOnlyElement(srv.getAllRealEstates());
		assertThat(loadedModel.getArea(), is(equalTo(area)));
		assertThat(loadedModel.getPrice(), is(equalTo(4000)));
		assertThat(loadedModel.getCoord().getLatDegree(), is(closeTo(100.44, 0.0001)));
		assertThat(loadedModel.getCoord().getLongDegree(), is(closeTo(-33.10, 0.0001)));
	}

}
