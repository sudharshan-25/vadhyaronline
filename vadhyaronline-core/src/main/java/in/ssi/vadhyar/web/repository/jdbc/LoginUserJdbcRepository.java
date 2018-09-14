package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.constants.CommonConstants;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserJdbcRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    public LoginUserJdbcRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String LOCK_USER_QUERY =
            " UPDATE USER_MASTER SET LOGIN_STATUS_ID = :LOGIN_STATUS_ID WHERE USER_ID = :USER_ID ";

    private static final String UPDATED_FAILED_ATTEMPT_QUERY =
            " UPDATE USER_MASTER SET LOGIN_FAILED_ATTEMPT = :LOGIN_FAILED_ATTEMPT WHERE USER_ID = :USER_ID ";

    private static final String UPDATED_LOGOUT_QUERY =
            " UPDATE USER_MASTER SET LATEST_LOGIN_TOKEN = :LATEST_LOGIN_TOKEN, LOGIN_STATUS_ID = :LOGIN_STATUS_ID " +
                    " WHERE USER_ID = :USER_ID ";

    public void lockUser(Integer userId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("LOGIN_STATUS_ID", CommonConstants.StatusConstants.STATUS_LOCKED_ID);
        map.addValue("USER_ID", userId);
        jdbcOperations.update(LOCK_USER_QUERY, map);
    }

    public void updatedFailedAttempts(Integer userId, Integer failedAttempts) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("USER_ID", userId);
        map.addValue("LOGIN_FAILED_ATTEMPT", failedAttempts);
        jdbcOperations.update(UPDATED_FAILED_ATTEMPT_QUERY, map);
    }

    public void updateLogOut(Integer userId){
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("USER_ID", userId);
        map.addValue("LOGIN_STATUS_ID", CommonConstants.StatusConstants.STATUS_ACTIVE_ID);
        map.addValue("LATEST_LOGIN_TOKEN", null);
        jdbcOperations.update(UPDATED_LOGOUT_QUERY, map);
    }

}
