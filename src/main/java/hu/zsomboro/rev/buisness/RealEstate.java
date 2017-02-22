package hu.zsomboro.rev.buisness;

import java.util.List;

import com.google.common.base.Joiner;

import hu.zsomboro.rev.ui.RealEstateUI;

public class RealEstate {

	private final Coordinate coord;
	private final double area;
	private final int price;
	private final int id;

	public RealEstate(Coordinate coord, double area) {
		super();
		this.coord = coord;
		this.area = area;
		this.price = 0;
		this.id = 0;
	}

	public RealEstate(Coordinate coord, double area, int price, int id) {
		super();
		this.coord = coord;
		this.area = area;
		this.price = price;
		this.id = id;
	}

	public RealEstate withNewPrice(List<PriceAverage> averages) {

		double minDistance = Double.POSITIVE_INFINITY;
		int price = 0;
		for (PriceAverage priceAverage : averages) {
			double newDistance = this.coord.distance(priceAverage.getCoord());
			if (Math.abs(minDistance - newDistance) <= 0.0001) {
				int newPrice = (int) Math.round(priceAverage.getAvgPrice() * area);
				price = newPrice < price ? newPrice : price;
			} else if (newDistance < minDistance) {
				minDistance = newDistance;
				price = (int) Math.round(priceAverage.getAvgPrice() * area);
			}
		}
		return new RealEstate(this.coord, this.area, price, this.id);
	}

	public Coordinate getCoord() {
		return coord;
	}

	public String getLongitudeCoordinates() {
		return Joiner.on(" ").join(coord.getLongitudeCoordinateDegree(), coord.getLongitudeCoordinateMinute(),
				coord.getLongitudeCoordinateSeconds(), coord.getLongitudeDirection()).toString();
	}

	public String getLatitudeCoordinates() {
		return Joiner.on(" ").join(coord.getLatitudeCoordinateDegree(), coord.getLatitudeCoordinateMinute(),
				coord.getLatitudeCoordinateSeconds(), coord.getLatitudeDirection()).toString();
	}

	public double getArea() {
		return area;
	}

	public int getPrice() {
		return price;
	}

	public RealEstateUI toUIModel() {
		RealEstateUI uiModel = new RealEstateUI();
		uiModel.setArea(area);
		uiModel.setCoordLatitude(getLatitudeCoordinates());
		uiModel.setCoordLongitude(getLongitudeCoordinates());
		uiModel.setPrice(price);
		uiModel.setId(id);
		return uiModel;
	}
}
