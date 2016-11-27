package fei.tcc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thiagoretondar on 27/11/16.
 */
@Repository
public class AppUsageRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AppUsageRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public LinkedHashMap<String, Integer> getQuantityForEachAppRepository(Integer userId, LocalDateTime timeStart, LocalDateTime timeEnd) {

        String sql = "SELECT app_name AS appname, COUNT(distinct datetime_used) AS quantity FROM parental_control.app_usage " +
                "WHERE user_id = :userId " +
                "AND datetime_used >= :timeStart " +
                "AND datetime_used <= :timeEnd " +
                "GROUP BY appname ORDER BY quantity DESC";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("timeStart", timeStart.toString());
        parameters.put("timeEnd", timeEnd.toString());

        SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);


        LinkedHashMap<String, Integer> result = namedParameterJdbcTemplate.query(sql, parameterSource,
                new ResultSetExtractor<LinkedHashMap<String, Integer>>() {
                    @Override
                    public LinkedHashMap<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        LinkedHashMap<String, Integer> mapRet = new LinkedHashMap<>();
                        while (rs.next()) {
                            mapRet.put(rs.getString("appname"), rs.getInt("quantity"));
                        }
                        return mapRet;
                    }
                }
        );

        return result;
    }

    public List<String> findAllAppsUsed(Integer userId, LocalDateTime timeStart, LocalDateTime timeEnd) {

        String sql = "SELECT DISTINCT app_name AS appname " +
                "FROM parental_control.app_usage " +
                "WHERE user_id = :userId " +
                "AND datetime_used >= :timeStart " +
                "AND datetime_used <= :timeEnd";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("timeStart", timeStart.toString());
        parameters.put("timeEnd", timeEnd.toString());

        SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);

        List<String> result = namedParameterJdbcTemplate.queryForList(sql, parameterSource, String.class);

        return result;
    }
}