package fei.tcc.repository;

import fei.tcc.dto.LocationUsageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thiagoretondar on 27/11/16.
 */
@Repository
public class LocationUsageRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public LocationUsageRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<LocationUsageDto> findAllAtLastDay(Integer userId) {

        String sql = "SELECT latitude, longitude" +
                " FROM parental_control.location_used" +
                " WHERE user_id = :userId" +
                " AND datetime_used >= (NOW() - INTERVAL 1 DAY)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);

        List<LocationUsageDto> locationUsageDtos = namedParameterJdbcTemplate.query(sql, parameterSource,
                new ResultSetExtractor<List<LocationUsageDto>>() {
                    @Override
                    public List<LocationUsageDto> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<LocationUsageDto> locations = new ArrayList<LocationUsageDto>();

                        int i = 0;
                        while (resultSet.next()) {
                            LocationUsageDto location = new LocationUsageDto();
                            location.setId(i++);
                            location.setLatitude(resultSet.getDouble("latitude"));
                            location.setLongitude(resultSet.getDouble("longitude"));

                            locations.add(location);
                        }

                        return locations;
                    }
                });

        return locationUsageDtos;

    }
}
