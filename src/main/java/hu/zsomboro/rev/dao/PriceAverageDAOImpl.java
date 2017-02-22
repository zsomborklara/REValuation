package hu.zsomboro.rev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import hu.zsomboro.rev.buisness.Coordinate;
import hu.zsomboro.rev.buisness.PriceAverage;

@Repository
public class PriceAverageDAOImpl implements PriceAverageDAO {

	private static final String FIND_ALL = "select * from real_estate_avg_prices";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<PriceAverage> getAllPriceAverages() {

		return namedParameterJdbcTemplate.query(FIND_ALL, new RowMapper<PriceAverage>() {

			@Override
			public PriceAverage mapRow(ResultSet rs, int index) throws SQLException {
				int avgPrice = rs.getInt("avg_price");
				double coordLongitude = rs.getDouble("coord_long");
				double coordLatitude = rs.getDouble("coord_lat");
				Coordinate coordinate = Coordinate.decimalDegreeBuilder().withLatitudeDegree(coordLatitude).withLongitudeDegree(coordLongitude).build();
				return new PriceAverage(coordinate, avgPrice);
			}
		});
	}

}
