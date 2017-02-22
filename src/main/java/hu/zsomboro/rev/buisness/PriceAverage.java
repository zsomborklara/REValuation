package hu.zsomboro.rev.buisness;

public class PriceAverage {

	private final Coordinate coord;
	private final int avgPrice;

	public PriceAverage(Coordinate coord, int avgPrice) {
		super();
		this.coord = coord;
		this.avgPrice = avgPrice;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public double getAvgPrice() {
		return avgPrice;
	}
}
