package hu.zsomboro.rev.buisness;

import java.util.Objects;

public class Coordinate {

	private final double latDegree;
	private final double longDegree;

	private Coordinate(double latDegree, double longDegree) {
		super();
		this.latDegree = latDegree;
		this.longDegree = longDegree;
	}

	public static DecimalDegreeBuilder decimalDegreeBuilder() {
		return new DecimalDegreeBuilder();
	}

	public double distance(Coordinate other) {

		int R = 6371000; // meters
		double latRadThis = toRadians(this.latDegree);
		double latRadOther = toRadians(other.latDegree);
		double deltaLatRad = toRadians(other.latDegree - this.latDegree);
		double deltaLongRad = toRadians(other.longDegree - this.longDegree);

		double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) + Math.cos(latRadThis) * Math.cos(latRadOther)
				* Math.sin(deltaLongRad / 2) * Math.sin(deltaLongRad / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double d = R * c;
		return d;

	}

	private double toRadians(double decimalDegrees) {
		return decimalDegrees * Math.PI / 180.;
	}

	public double getLatDegree() {
		return latDegree;
	}

	public double getLongDegree() {
		return longDegree;
	}

	public int getLatitudeCoordinateDegree() {
		return getIntegerPart(latDegree);
	}

	public int getLatitudeCoordinateMinute() {
		double minute = latDegree % 1 * 60;
		return getIntegerPart(minute);
	}

	public int getLatitudeCoordinateSeconds() {
		double minute = latDegree % 1 * 60;
		return Math.abs((int) Math.round(minute % 1 * 60));
	}

	public int getLongitudeCoordinateDegree() {
		return getIntegerPart(longDegree);
	}

	public int getLongitudeCoordinateMinute() {
		double minute = longDegree % 1 * 60;
		return getIntegerPart(minute);
	}

	public int getLongitudeCoordinateSeconds() {
		double minute = longDegree % 1 * 60;
		return Math.abs((int) Math.round(minute % 1 * 60));
	}

	public String getLongitudeDirection() {
		if (longDegree < 0.0) {
			return Direction.W.toString();
		} else {
			return Direction.E.toString();
		}
	}

	public String getLatitudeDirection() {
		if (latDegree < 0.0) {
			return Direction.S.toString();
		} else {
			return Direction.N.toString();
		}
	}

	public static LatitudeLongitudeCoordinateBuilder latitudeAndLongitudeBuilder() {
		return new LatitudeLongitudeCoordinateBuilder();
	}

	private int getIntegerPart(double fpNumber) {
		return Math.abs((int)Math.round(fpNumber - fpNumber % 1));
	}

	public static class DecimalDegreeBuilder {
		private Double latDegree;
		private Double longDegree;

		public Coordinate build() {
			Objects.requireNonNull(latDegree, "Latitude degrees not provided");
			Objects.requireNonNull(longDegree, "Longitude degrees not provided");

			return new Coordinate(latDegree, longDegree);
		}

		public DecimalDegreeBuilder withLatitudeDegree(Double latDegree) {
			this.latDegree = latDegree;
			return this;
		}

		public DecimalDegreeBuilder withLongitudeDegree(Double longDegree) {
			this.longDegree = longDegree;
			return this;
		}
	}

	public static class LatitudeLongitudeCoordinateBuilder {

		private Integer latDegree;
		private Integer longDegree;
		private Integer latMinute;
		private Integer longMinute;
		private Integer latSecond;
		private Integer longSecond;
		private Direction latDir;
		private Direction longDir;

		public Coordinate build() {
			Objects.requireNonNull(latDegree, "Latitude degrees not provided");
			Objects.requireNonNull(longDegree, "Logitude degrees not provided");
			Objects.requireNonNull(latMinute, "Latitude minutes not provided");
			Objects.requireNonNull(longMinute, "Logitude minutes not provided");
			Objects.requireNonNull(latSecond, "Latitude seconds not provided");
			Objects.requireNonNull(longSecond, "Logitude seconds not provided");

			if (!Direction.N.equals(latDir) && !Direction.S.equals(latDir)) {
				throw new IllegalArgumentException("Invalid latitude direction " + latDir);
			}

			if (!Direction.E.equals(longDir) && !Direction.W.equals(longDir)) {
				throw new IllegalArgumentException("Invalid latitude direction " + longDir);
			}

			return new Coordinate(latDir.multiplier * (latDegree + latMinute / 60.0 + latSecond / 3600.0),
					longDir.multiplier * (longDegree + longMinute / 60.0 + longSecond / 3600.0));
		}

		public LatitudeLongitudeCoordinateBuilder withLatitudeDegree(Integer latDegree) {
			this.latDegree = latDegree;
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLongitudeDegree(Integer longDegree) {
			this.longDegree = longDegree;
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLatitudeMinute(Integer latMinute) {
			this.latMinute = latMinute;
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLongitudeMinute(Integer longMinute) {
			this.longMinute = longMinute;
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLatitudeSeconds(Integer latSecond) {
			this.latSecond = latSecond;
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLongitudeSeconds(Integer longSecond) {
			this.longSecond = longSecond;
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLongitudeDirection(String direction) {
			this.longDir = Direction.valueOf(direction);
			return this;
		}

		public LatitudeLongitudeCoordinateBuilder withLatitudeDirection(String direction) {
			this.latDir = Direction.valueOf(direction);
			return this;
		}
	}

	public enum Direction {
		N(1), S(-1), E(1), W(-1);

		private final int multiplier;

		private Direction(int multiplier) {
			this.multiplier = multiplier;
		}
	}
}
