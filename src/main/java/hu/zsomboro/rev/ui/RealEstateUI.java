package hu.zsomboro.rev.ui;

import java.util.List;

import com.google.common.base.Splitter;

import hu.zsomboro.rev.buisness.Coordinate;
import hu.zsomboro.rev.buisness.Coordinate.LatitudeLongitudeCoordinateBuilder;
import hu.zsomboro.rev.buisness.RealEstate;

public class RealEstateUI {

	private Integer id;

	private Double area;

	private Integer price;

	private String coordLatitude;

	private String coordLongitude;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCoordLatitude() {
		return coordLatitude;
	}

	public void setCoordLatitude(String coordLatitude) {
		this.coordLatitude = coordLatitude;
	}

	public String getCoordLongitude() {
		return coordLongitude;
	}

	public void setCoordLongitude(String coordLongitude) {
		this.coordLongitude = coordLongitude;
	}

	public RealEstate toBusinessModel(){
		List<String> latList = Splitter.on(" ").omitEmptyStrings().splitToList(coordLatitude);
		List<String> longList = Splitter.on(" ").omitEmptyStrings().splitToList(coordLongitude);
		LatitudeLongitudeCoordinateBuilder builder = Coordinate.latitudeAndLongitudeBuilder();
		builder.withLatitudeDegree(Integer.valueOf(latList.get(0))).
		withLatitudeMinute(Integer.valueOf(latList.get(1))).
		withLatitudeSeconds(Integer.valueOf(latList.get(2))).
		withLatitudeDirection(latList.get(3));

		builder.withLongitudeDegree(Integer.valueOf(longList.get(0))).
		withLongitudeMinute(Integer.valueOf(longList.get(1))).
		withLongitudeSeconds(Integer.valueOf(longList.get(2))).
		withLongitudeDirection(longList.get(3));

		Coordinate coordinate = builder.build();

		return new RealEstate(coordinate, area);

	}

}
