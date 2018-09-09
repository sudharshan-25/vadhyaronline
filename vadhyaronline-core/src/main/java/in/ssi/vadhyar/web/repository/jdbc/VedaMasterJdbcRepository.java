package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VedaMasterJdbcRepository implements IJdbcRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    public VedaMasterJdbcRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private final String CHOICE_SELECT_SQL = " SELECT VEDA_ID, VEDA_NAME FROM VEDA_MASTER ";

    @Override
    public List<DropDownChoice> getDropDownList() {
        return jdbcOperations.query(CHOICE_SELECT_SQL, getDropDownMapper());
    }

    @Override
    public List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId) {
        throw new VadhyarOnlineException("Method not implemented");
    }

    @Override
    public RowMapper<DropDownChoice> getDropDownMapper() {
        return (rs, rowNum) -> new DropDownChoice(rs.getInt("VEDA_ID"), rs.getString("VEDA_NAME"));
    }

    @Override
    public boolean isExistsByKey(String key) {
        throw new VadhyarOnlineException("Method not implemented");
    }

    @Override
    public boolean canDelete(Integer id) {
        throw new VadhyarOnlineException("Method not implemented");
    }
}
