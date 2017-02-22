package hu.zsomboro.rev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hu.zsomboro.rev.buisness.Coordinate;
import hu.zsomboro.rev.buisness.RealEstate;

@Repository
public class RealEstateDAOImpl implements RealEstateDAO {

	private static final String FIND_ALL = "select * from price_estimates";
	private static final String INSERT_RE = "insert into price_estimates(area, price, coord_long, coord_lat) "
			+ "values ( :area, :price, :coordLong, :coordLat)";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<RealEstate> getAllRealEstates() {
		return namedParameterJdbcTemplate.query(FIND_ALL, new RowMapper<RealEstate>() {

			@Override
			public RealEstate mapRow(ResultSet rs, int index) throws SQLException {
				int calculatedPrice = rs.getInt("price");
				int id = rs.getInt("id");
				double area = rs.getDouble("area");
				double coordLongitude = rs.getDouble("coord_long");
				double coordLatitude = rs.getDouble("coord_lat");
				Coordinate coordinate = Coordinate.decimalDegreeBuilder().withLatitudeDegree(coordLatitude).withLongitudeDegree(coordLongitude).build();
				return new RealEstate(coordinate, area, calculatedPrice, id);
			}
		});
	}

	@Override
	public void saveRealEstate(RealEstate re) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("area", re.getArea());
		params.addValue("price", re.getPrice());
		params.addValue("coordLong", re.getCoord().getLongDegree());
		params.addValue("coordLat", re.getCoord().getLatDegree());

		namedParameterJdbcTemplate.update(INSERT_RE, params, keyHolder);
		// log out new key keyHolder.getKey()
	}

}
