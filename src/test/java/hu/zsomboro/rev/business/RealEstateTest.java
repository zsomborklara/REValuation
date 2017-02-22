package hu.zsomboro.rev.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.google.common.collect.Lists;

import hu.zsomboro.rev.buisness.Coordinate;
import hu.zsomboro.rev.buisness.Coordinate.LatitudeLongitudeCoordinateBuilder;
import hu.zsomboro.rev.buisness.PriceAverage;
import hu.zsomboro.rev.buisness.RealEstate;

public class RealEstateTest {

	@Test
	public void canDisplay_Correct_StringCoordRepresentation() {

		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(21).withLatitudeMinute(11).withLatitudeSeconds(60).withLatitudeDirection("N");
		builder1.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(9).withLongitudeDirection("W");
		Coordinate coordinate1 = builder1.build();

		LatitudeLongitudeCoordinateBuilder builder2 = Coordinate.latitudeAndLongitudeBuilder();
		builder2.withLatitudeDegree(90).withLatitudeMinute(5).withLatitudeSeconds(45).withLatitudeDirection("S");
		builder2.withLongitudeDegree(111).withLongitudeMinute(0).withLongitudeSeconds(15).withLongitudeDirection("E");
		Coordinate coordinate2 = builder2.build();

		RealEstate re1 = new RealEstate(coordinate1, 55);
		RealEstate re2 = new RealEstate(coordinate2, 55);

		assertThat(re1.getLatitudeCoordinates(), is(equalTo("21 11 60 N")));
		assertThat(re1.getLongitudeCoordinates(), is(equalTo("150 20 9 W")));

		assertThat(re2.getLatitudeCoordinates(), is(equalTo("90 5 45 S")));
		assertThat(re2.getLongitudeCoordinates(), is(equalTo("111 0 15 E")));
	}

	@Test
	public void canCalculate_WithMissingAverages() {
		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(21).withLatitudeMinute(11).withLatitudeSeconds(60).withLatitudeDirection("N");
		builder1.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(9).withLongitudeDirection("W");
		Coordinate coordinate1 = builder1.build();

		RealEstate re = new RealEstate(coordinate1, 55);
		RealEstate reWithPrice = re.withNewPrice(Lists.newArrayList());

		assertThat(reWithPrice.getPrice(), is(equalTo(0)));
	}

	@Test
	public void canCalculate_RealEstatePrice() {

		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(21).withLatitudeMinute(11).withLatitudeSeconds(60).withLatitudeDirection("N");
		builder1.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(9).withLongitudeDirection("W");
		Coordinate coordinate1 = builder1.build();

		LatitudeLongitudeCoordinateBuilder builder2 = Coordinate.latitudeAndLongitudeBuilder();
		builder2.withLatitudeDegree(90).withLatitudeMinute(5).withLatitudeSeconds(45).withLatitudeDirection("S");
		builder2.withLongitudeDegree(111).withLongitudeMinute(0).withLongitudeSeconds(15).withLongitudeDirection("E");
		Coordinate coordinate2 = builder2.build();

		RealEstate re = new RealEstate(coordinate1, 55);

		PriceAverage average = new PriceAverage(coordinate2, 100);

		RealEstate reWithPrice = re.withNewPrice(Lists.newArrayList(average));

		assertThat(reWithPrice.getPrice(), is(equalTo(5500)));

	}

	@Test
	public void priceCalculation_WillTakeClosest_RealEstatePrice() {

		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(21).withLatitudeMinute(11).withLatitudeSeconds(60).withLatitudeDirection("N");
		builder1.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(9).withLongitudeDirection("W");
		Coordinate coordinate1 = builder1.build();

		LatitudeLongitudeCoordinateBuilder builder2 = Coordinate.latitudeAndLongitudeBuilder();
		builder2.withLatitudeDegree(90).withLatitudeMinute(5).withLatitudeSeconds(45).withLatitudeDirection("S");
		builder2.withLongitudeDegree(111).withLongitudeMinute(0).withLongitudeSeconds(15).withLongitudeDirection("E");
		Coordinate coordinate2 = builder2.build();

		LatitudeLongitudeCoordinateBuilder builder3 = Coordinate.latitudeAndLongitudeBuilder();
		builder3.withLatitudeDegree(21).withLatitudeMinute(8).withLatitudeSeconds(45).withLatitudeDirection("N");
		builder3.withLongitudeDegree(150).withLongitudeMinute(19).withLongitudeSeconds(10).withLongitudeDirection("W");
		Coordinate coordinate3 = builder3.build();

		LatitudeLongitudeCoordinateBuilder builder4 = Coordinate.latitudeAndLongitudeBuilder();
		builder4.withLatitudeDegree(25).withLatitudeMinute(8).withLatitudeSeconds(45).withLatitudeDirection("N");
		builder4.withLongitudeDegree(100).withLongitudeMinute(19).withLongitudeSeconds(10).withLongitudeDirection("W");
		Coordinate coordinate4 = builder4.build();

		RealEstate re = new RealEstate(coordinate1, 55);

		PriceAverage farAverage1 = new PriceAverage(coordinate2, 100);
		PriceAverage closeAverage = new PriceAverage(coordinate3, 10);
		PriceAverage farAverage2 = new PriceAverage(coordinate4, 200);

		RealEstate reWithPrice = re.withNewPrice(Lists.newArrayList(farAverage1, closeAverage, farAverage2));

		assertThat(reWithPrice.getPrice(), is(equalTo(550)));

	}


	@Test
	public void priceCalculation_WillTakeLowestAverage_WhenDistanceIsSame() {

		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(21).withLatitudeMinute(11).withLatitudeSeconds(60).withLatitudeDirection("N");
		builder1.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(9).withLongitudeDirection("W");
		Coordinate coordinate1 = builder1.build();

		LatitudeLongitudeCoordinateBuilder builder2 = Coordinate.latitudeAndLongitudeBuilder();
		builder2.withLatitudeDegree(90).withLatitudeMinute(5).withLatitudeSeconds(45).withLatitudeDirection("S");
		builder2.withLongitudeDegree(111).withLongitudeMinute(0).withLongitudeSeconds(15).withLongitudeDirection("E");
		Coordinate coordinate2 = builder2.build();

		LatitudeLongitudeCoordinateBuilder builder3 = Coordinate.latitudeAndLongitudeBuilder();
		builder3.withLatitudeDegree(21).withLatitudeMinute(8).withLatitudeSeconds(45).withLatitudeDirection("N");
		builder3.withLongitudeDegree(150).withLongitudeMinute(19).withLongitudeSeconds(10).withLongitudeDirection("W");
		Coordinate coordinate3 = builder3.build();


		RealEstate re = new RealEstate(coordinate1, 55);

		PriceAverage farAverage1 = new PriceAverage(coordinate2, 100);
		PriceAverage closeAverage1 = new PriceAverage(coordinate3, 50);
		PriceAverage closeAverage2 = new PriceAverage(coordinate3, 20);

		RealEstate reWithPrice = re.withNewPrice(Lists.newArrayList(farAverage1, closeAverage1, closeAverage2));

		assertThat(reWithPrice.getPrice(), is(equalTo(1100)));

	}

}
