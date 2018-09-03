package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.domain.EventCategory;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class EventCategoryJdbcRepository implements AbstractJdbcRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    private LoginUserContext loginUserContext;

    public EventCategoryJdbcRepository(NamedParameterJdbcOperations jdbcOperations, LoginUserContext loginUserContext) {
        this.jdbcOperations = jdbcOperations;
        this.loginUserContext = loginUserContext;
    }

    private static final String DROP_DOWN_SQL =
            " SELECT EVENT_CATEGORY_ID, EVENT_CATEGORY_NAME FROM  EVENT_CATEGORY WHERE APPROVED = :APPROVED ";

    private static final String EXIST_BY_NAME =
            " SELECT 1 FROM EVENT_CATEGORY WHERE LOWER(EVENT_CATEGORY_NAME) = :EVENT_CATEGORY_NAME ";

    private static final String WHERE_BY_ID = " WHERE EVENT_CATEGORY_ID = :EVENT_CATEGORY_ID ";

    private static final String UPDATE_EVENT_CATEGORY =
            " UPDATE EVENT_CATEGORY SET EVENT_CATEGORY_NAME = :EVENT_CATEGORY_NAME " + WHERE_BY_ID;

    private static final String APPROVE_EVENT_CATEGORY =
            " UPDATE EVENT_CATEGORY SET APPROVED = :APPROVED, APPROVED_BY = :APPROVED_BY, APPROVED_ON = :APPROVED_ON "
                    + WHERE_BY_ID;

    private static final String CAN_DELETE_QUERY = " SELECT 1 FROM EVENT_TYPE " + WHERE_BY_ID;

    @Override
    public List<DropDownChoice> getDropDownList() {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("APPROVED", Boolean.TRUE);
        return jdbcOperations.query(DROP_DOWN_SQL, map, getDropDownMapper());
    }

    @Override
    public List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId) {
        throw new VadhyarOnlineException("Method not implemented");
    }

    @Override
    public boolean isExistsByKey(String eventCategoryName) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_CATEGORY_NAME", eventCategoryName.toLowerCase());
        return jdbcOperations.query(EXIST_BY_NAME, map, ResultSet::next);
    }

    public void updateEventCategory(EventCategory eventCategory) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_CATEGORY_NAME", eventCategory.getEventCategoryName());
        map.addValue("EVENT_CATEGORY_ID", eventCategory.getEventCategoryId());
        jdbcOperations.update(UPDATE_EVENT_CATEGORY, map);
    }

    public void approveEventCategory(int eventCategoryId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_CATEGORY_ID", eventCategoryId);
        map.addValue("APPROVED", Boolean.TRUE);
        map.addValue("APPROVED_BY", loginUserContext.getCurrentUser().getUserId());
        map.addValue("APPROVED_ON", Timestamp.from(Instant.now()));
        jdbcOperations.update(APPROVE_EVENT_CATEGORY, map);
    }

    @Override
    public RowMapper<DropDownChoice> getDropDownMapper() {
        return ((rs, rowNum) ->
                new DropDownChoice(rs.getInt("EVENT_CATEGORY_ID"), rs.getString("EVENT_CATEGORY_Name"))
        );
    }

    @Override
    public boolean canDelete(Integer id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_CATEGORY_ID", id);
        return !jdbcOperations.query(CAN_DELETE_QUERY, map, ResultSet::next);
    }
}
