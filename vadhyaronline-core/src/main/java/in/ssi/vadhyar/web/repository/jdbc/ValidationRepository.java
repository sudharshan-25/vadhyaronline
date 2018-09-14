package in.ssi.vadhyar.web.repository.jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class ValidationRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    public ValidationRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String UNIQUE_MOBILE_QUERY =
            " SELECT 1 FROM USER_MASTER WHERE USER_MOBILE = :USER_MOBILE ";

    private static final String UNIQUE_EMAIL_QUERY =
            " SELECT 1 FROM USER_MASTER WHERE USER_EMAIL = :USER_EMAIL ";

    private static final String UNIQUE_USER_QUERY =
            " SELECT 1 FROM USER_MASTER WHERE USER_NAME = :USER_NAME ";

    public Boolean isUniqueMobile(String mobileNumber) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("USER_MOBILE", mobileNumber);
        return jdbcOperations.query(UNIQUE_MOBILE_QUERY, map, rs -> !rs.next());
    }


    public Boolean isUniqueEmail(String emailAddress) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("USER_EMAIL", emailAddress);
        return jdbcOperations.query(UNIQUE_EMAIL_QUERY, map, rs -> !rs.next());
    }

    public Boolean isUniqueUserName(String userName) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("USER_NAME", userName);
        return jdbcOperations.query(UNIQUE_USER_QUERY, map, rs -> !rs.next());
    }

}
