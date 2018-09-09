package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.domain.Soothram;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class SoothramJdbcRepository implements IJdbcRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    private LoginUserContext loginUserContext;

    public SoothramJdbcRepository(NamedParameterJdbcOperations jdbcOperations, LoginUserContext loginUserContext) {
        this.jdbcOperations = jdbcOperations;
        this.loginUserContext = loginUserContext;
    }

    private static final String SOOTHRAM_DROPDOWN_QUERY =
            " SELECT SOOTHRAM_ID, SOOTHRAM_NAME FROM SOOTHRAM_MASTER WHERE APPROVED =:APPROVED ";

    private static final String SOOTHRAM_EXISTS_QUERY =
            " SELECT 1 FROM SOOTHRAM_MASTER WHERE SOOTHRAM_NAME =:SOOTHRAM_NAME ";

    private static final String SOOTHRAM_UPDATE_QUERY =
            " UPDATE SOOTHRAM_MASTER SET SOOTHRAM_NAME = :SOOTHRAM_NAME WHERE SOOTHRAM_ID = :SOOTHRAM_ID ";

    private static final String SOOTHRAM_APPROVE_QUERY =
            " UPDATE SOOTHRAM_MASTER SET APPROVED =:APPROVED, APPROVED_BY = :APPROVED_BY, APPROVED_ON = :APPROVED_ON " +
                    " WHERE SOOTHRAM_ID = :SOOTHRAM_ID ";

    @Override
    public List<DropDownChoice> getDropDownList() {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("APPROVED", Boolean.TRUE);
        return jdbcOperations.query(SOOTHRAM_DROPDOWN_QUERY, map, getDropDownMapper());
    }

    @Override
    public List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId) {
        throw new VadhyarOnlineException("Method not implemented");
    }

    @Override
    public RowMapper<DropDownChoice> getDropDownMapper() {
        return (rs, rowNum) ->
                new DropDownChoice(rs.getInt("SOOTHRAM_ID"), rs.getString("SOOTHRAM_Name"));
    }

    @Override
    public boolean isExistsByKey(String key) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("SOOTHRAM_NAME", key);
        return jdbcOperations.query(SOOTHRAM_EXISTS_QUERY, map, ResultSet::next);
    }

    @Override
    public boolean canDelete(Integer id) {
        // TODO
        return false;
    }

    public void updateSoothram(Soothram soothram) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("SOOTHRAM_NAME", soothram.getSoothramName());
        map.addValue("SOOTHRAM_ID", soothram.getSoothramId());
        jdbcOperations.update(SOOTHRAM_UPDATE_QUERY, map);
    }

    public void approveSoothram(Integer soothramId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("SOOTHRAM_ID", soothramId);
        map.addValue("APPROVED", Boolean.TRUE);
        map.addValue("APPROVED_BY", loginUserContext.getCurrentUser().getUserId());
        map.addValue("APPROVED_ON", Timestamp.from(Instant.now()));
        jdbcOperations.update(SOOTHRAM_APPROVE_QUERY, map);
    }
}
