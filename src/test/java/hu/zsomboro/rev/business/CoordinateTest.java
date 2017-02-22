package hu.zsomboro.rev.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import hu.zsomboro.rev.buisness.Coordinate;
import hu.zsomboro.rev.buisness.Coordinate.DecimalDegreeBuilder;
import hu.zsomboro.rev.buisness.Coordinate.LatitudeLongitudeCoordinateBuilder;

public class CoordinateTest {

	@Test
	public void canBuild_Coordinate_UsingDecimalDegrees() {
		DecimalDegreeBuilder builder = Coordinate.decimalDegreeBuilder();
		builder.withLatitudeDegree(10.333);
		builder.withLongitudeDegree(-33.10);
		Coordinate coordinate = builder.build();

		assertThat(coordinate.getLatDegree(), is(equalTo(10.333)));
		assertThat(coordinate.getLongDegree(), is(equalTo(-33.10)));
	}

	@Test(expected = NullPointerException.class)
	public void willThrow_NPE_IfDecBuilderIncomplete() {
		DecimalDegreeBuilder builder = Coordinate.decimalDegreeBuilder();
		builder.withLatitudeDegree(10.333);
		builder.build();
	}

	@Test
	public void canBuild_Coordinate_UsingLatLongDegrees() {
		LatitudeLongitudeCoordinateBuilder builder = Coordinate.latitudeAndLongitudeBuilder();
		builder.withLatitudeDegree(38).withLatitudeMinute(53).withLatitudeSeconds(55).withLatitudeDirection("N");
		builder.withLongitudeDegree(77).withLongitudeMinute(2).withLongitudeSeconds(16).withLongitudeDirection("E");
		Coordinate coordinate = builder.build();

		assertThat(coordinate.getLatitudeCoordinateDegree(), is(equalTo(38)));
		assertThat(coordinate.getLongitudeCoordinateDegree(), is(equalTo(77)));
		assertThat(coordinate.getLatitudeCoordinateMinute(), is(equalTo(53)));
		assertThat(coordinate.getLongitudeCoordinateMinute(), is(equalTo(2)));
		assertThat(coordinate.getLatitudeCoordinateSeconds(), is(equalTo(55)));
		assertThat(coordinate.getLongitudeCoordinateSeconds(), is(equalTo(16)));
	}

	@Test(expected = NullPointerException.class)
	public void willThrow_NPE_IfLatLongBuilderIncomplete() {
		LatitudeLongitudeCoordinateBuilder builder = Coordinate.latitudeAndLongitudeBuilder();
		builder.withLatitudeDegree(38).withLatitudeSeconds(55);
		builder.withLongitudeDegree(77).withLongitudeMinute(2);
		builder.build();
	}

	@Test
	public void canConvert_Between_DecimalAndLatLong() {

		LatitudeLongitudeCoordinateBuilder builder = Coordinate.latitudeAndLongitudeBuilder();
		builder.withLatitudeDegree(10).withLatitudeMinute(50).withLatitudeSeconds(44).withLatitudeDirection("N");
		builder.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(10).withLongitudeDirection("E");
		Coordinate coordinate = builder.build();

		assertThat(coordinate.getLatDegree(), is(closeTo(10.8456, 0.0001)));
		assertThat(coordinate.getLongDegree(), is(closeTo(150.3361, 0.0001)));
	}

	@Test
	public void canConvert_Between_DecimalAndLatLong_Negative() {

		LatitudeLongitudeCoordinateBuilder builder = Coordinate.latitudeAndLongitudeBuilder();
		builder.withLatitudeDegree(10).withLatitudeMinute(50).withLatitudeSeconds(44).withLatitudeDirection("S");
		builder.withLongitudeDegree(150).withLongitudeMinute(20).withLongitudeSeconds(10).withLongitudeDirection("W");
		Coordinate coordinate = builder.build();

		assertThat(coordinate.getLatDegree(), is(closeTo(-10.8456, 0.0001)));
		assertThat(coordinate.getLongDegree(), is(closeTo(-150.3361, 0.0001)));
	}

	@Test
	public void canCalculate_Distance_BetweenFarPoints() {

		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(50).withLatitudeMinute(3).withLatitudeSeconds(59).withLatitudeDirection("N");
		builder1.withLongitudeDegree(5).withLongitudeMinute(42).withLongitudeSeconds(53).withLongitudeDirection("E");
		Coordinate c1 = builder1.build();

		LatitudeLongitudeCoordinateBuilder builder2 = Coordinate.latitudeAndLongitudeBuilder();
		builder2.withLatitudeDegree(58).withLatitudeMinute(38).withLatitudeSeconds(38).withLatitudeDirection("N");
		builder2.withLongitudeDegree(3).withLongitudeMinute(4).withLongitudeSeconds(12).withLongitudeDirection("E");
		Coordinate c2 = builder2.build();

		double distance = c1.distance(c2);
		// error of 1KM at an approx. distance of 950KM
		assertThat(distance/1000., is(closeTo(968.0, 1.)));
	}

	@Test
	public void canCalculate_Distance_BetweenClosePoints() {

		LatitudeLongitudeCoordinateBuilder builder1 = Coordinate.latitudeAndLongitudeBuilder();
		builder1.withLatitudeDegree(50).withLatitudeMinute(3).withLatitudeSeconds(59).withLatitudeDirection("N");
		builder1.withLongitudeDegree(5).withLongitudeMinute(42).withLongitudeSeconds(53).withLongitudeDirection("E");
		Coordinate c1 = builder1.build();

		LatitudeLongitudeCoordinateBuilder builder2 = Coordinate.latitudeAndLongitudeBuilder();
		builder2.withLatitudeDegree(50).withLatitudeMinute(38).withLatitudeSeconds(38).withLatitudeDirection("N");
		builder2.withLongitudeDegree(5).withLongitudeMinute(4).withLongitudeSeconds(12).withLongitudeDirection("E");
		Coordinate c2 = builder2.build();

		double distance = c1.distance(c2);
		// error of 100M at an approx. distance of 75KM
		assertThat(distance/1000., is(closeTo(78.84, 0.1)));
	}

}
