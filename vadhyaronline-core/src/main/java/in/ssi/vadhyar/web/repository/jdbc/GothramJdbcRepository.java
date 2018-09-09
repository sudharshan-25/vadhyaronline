package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.domain.Gothram;
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
public class GothramJdbcRepository implements IJdbcRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    private LoginUserContext loginUserContext;

    public GothramJdbcRepository(NamedParameterJdbcOperations jdbcOperations, LoginUserContext loginUserContext) {
        this.loginUserContext = loginUserContext;
        this.jdbcOperations = jdbcOperations;
    }

    private static final String GOTHRAM_DROPDOWN_QUERY =
            " SELECT GOTHRAM_ID, GOTHRAM_NAME FROM GOTHRAM_MASTER WHERE APPROVED = :APPROVED ";

    private static final String GOTHRAM_EXISTS_QUERY =
            " SELECT 1 FROM GOTHRAM_MASTER WHERE GOTHRAM_NAME =:GOTHRAM_NAME ";

    private static final String GOTHRAM_UPDATE_QUERY =
            " UPDATE GOTHRAM_MASTER SET GOTHRAM_NAME =:GOTHRAM_NAME WHERE GOTHRAM_ID = :GOTHRAM_ID ";

    private static final String GOTHRAM_APPROVE_QUERY =
            " UPDATE GOTHRAM_MASTER SET APPROVED =:APPROVED, APPROVED_BY = :APPROVED_BY, APPROVED_ON = :APPROVED_ON " +
                    " WHERE GOTHRAM_ID = :GOTHRAM_ID ";

    @Override
    public List<DropDownChoice> getDropDownList() {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("APPROVED", Boolean.TRUE);
        return jdbcOperations.query(GOTHRAM_DROPDOWN_QUERY, map, getDropDownMapper());
    }

    @Override
    public List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId) {
        throw new VadhyarOnlineException("Method not implemented");
    }

    @Override
    public RowMapper<DropDownChoice> getDropDownMapper() {
        return (rs, rowNum) ->
                new DropDownChoice(rs.getInt("GOTHRAM_ID"), rs.getString("GOTHRAM_NAME"));
    }

    @Override
    public boolean isExistsByKey(String key) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("GOTHRAM_NAME", key);
        return jdbcOperations.query(GOTHRAM_EXISTS_QUERY, map, ResultSet::next);
    }

    @Override
    public boolean canDelete(Integer id) {
        // TODO
        return false;
    }

    public void updateGothram(Gothram gothram) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("GOTHRAM_NAME", gothram.getGothramName());
        map.addValue("GOTHRAM_ID", gothram.getGothramId());
        jdbcOperations.update(GOTHRAM_UPDATE_QUERY, map);
    }

    public void approveGothram(Integer gothramId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("GOTHRAM_ID", gothramId);
        map.addValue("APPROVED", Boolean.TRUE);
        map.addValue("APPROVED_BY", loginUserContext.getCurrentUser().getUserId());
        map.addValue("APPROVED_ON", Timestamp.from(Instant.now()));
        jdbcOperations.update(GOTHRAM_APPROVE_QUERY, map);
    }

}
