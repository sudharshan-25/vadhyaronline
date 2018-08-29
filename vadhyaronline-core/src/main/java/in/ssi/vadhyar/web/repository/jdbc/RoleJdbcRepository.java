package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.constants.CommonConstants;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class RoleJdbcRepository implements AbstractJdbcRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private final String CHOICE_SELECT_SQL = " SELECT ROLE_ID, ROLE_NAME FROM ROLE_MASTER ";

    private final String IS_EXISTS_BY_KEY = " SELECT 1 FROM ROLE_MASTER WHERE LOWER(ROLE_NAME) = :ROLE_NAME";

    public static DropDownChoice ADMIN_CHOICE = new DropDownChoice(CommonConstants.RoleConstants.ADMIN_ROLE_ID,
            CommonConstants.RoleConstants.ADMIN_ROLE_NAME);

    @Override
    public List<DropDownChoice> getDropDownList() {
        return jdbcOperations.query(CHOICE_SELECT_SQL, getDropDownMapper());
    }

    @Override
    public List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId) {
        throw new VadhyarOnlineException("Method not implemented");
    }

    @Override
    public boolean isExistsByKey(String roleName) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("ROLE_NAME", roleName.toLowerCase());
        return jdbcOperations.query(IS_EXISTS_BY_KEY, map, ResultSet::next);
    }

    @Override
    public RowMapper<DropDownChoice> getDropDownMapper() {
        return ((rs, rowNum) ->
                new DropDownChoice(rs.getInt("ROLE_ID"), rs.getString("ROLE_NAME")));
    }
}
