package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.domain.EventType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class EventTypeJdbcRepository implements AbstractJdbcRepository {

    private LoginUserContext loginUserContext;

    private NamedParameterJdbcOperations jdbcOperations;

    public EventTypeJdbcRepository(NamedParameterJdbcOperations jdbcOperations, LoginUserContext loginUserContext) {
        this.jdbcOperations = jdbcOperations;
        this.loginUserContext = loginUserContext;
    }

    private static final String DROP_DOWN_SELECT_QUERY =
            " SELECT event_type_id, event_type_name FROM EVENT_TYPE WHERE APPROVED = :APPROVED ";

    private static final String DROP_DOWN_SELECT_QUERY_FOR_CRITERIA =
            " SELECT event_type_id, event_type_name FROM EVENT_TYPE WHERE APPROVED = :APPROVED " +
                    " AND EVENT_CATEGORY_ID = :EVENT_CATEGORY_ID ";

    private static final String EXISTS_BY_KEY_QUERY = " SELECT 1 FROM EVENT_TYPE WHERE event_type_name " +
            " = :EVENT_TYPE_NAME ";

    private static final String UPDATE_EVENT_TYPE = " UPDATE EVENT_TYPE SET EVENT_TYPE_NAME = :EVENT_TYPE_NAME , " +
            " EVENT_TYPE_DESC = :EVENT_TYPE_DESC WHERE EVENT_TYPE_ID = :EVENT_TYPE_ID ";

    private static final String APPROVE_EVENT_TYPE =
            " UPDATE EVENT_TYPE SET APPROVED = :APPROVED, APPROVED_BY = :APPROVED_BY, APPROVED_ON = :APPROVED_ON " +
                    " WHERE EVENT_TYPE_ID = :EVENT_TYPE_ID ";


    @Override
    public List<DropDownChoice> getDropDownList() {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("APPROVED", Boolean.TRUE);
        return jdbcOperations.query(DROP_DOWN_SELECT_QUERY, map, getDropDownMapper());
    }

    @Override
    public List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("APPROVED", Boolean.TRUE);
        map.addValue("EVENT_CATEGORY_ID", criteriaId.getCriteriaId());
        return jdbcOperations.query(DROP_DOWN_SELECT_QUERY_FOR_CRITERIA, map, getDropDownMapper());
    }

    @Override
    public RowMapper<DropDownChoice> getDropDownMapper() {
        return ((rs, i) ->
                new DropDownChoice(rs.getInt("event_type_id"), rs.getString("event_type_name"))
        );
    }

    @Override
    public boolean isExistsByKey(String key) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_TYPE_NAME", key);
        return jdbcOperations.query(EXISTS_BY_KEY_QUERY, map, ResultSet::next);
    }

    @Override
    public boolean canDelete(Integer id) {
        //TODO
        return false;
    }

    public void updateEventType(EventType eventType) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_TYPE_NAME", eventType.getEventTypeName());
        map.addValue("EVENT_TYPE_DESC", eventType.getEventTypeDescription());
        map.addValue("EVENT_TYPE_ID", eventType.getEventTypeId());
        jdbcOperations.update(UPDATE_EVENT_TYPE, map);
    }

    public void approveEventType(Integer eventTypeId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("EVENT_TYPE_ID", eventTypeId);
        map.addValue("APPROVED", Boolean.TRUE);
        map.addValue("APPROVED_BY", loginUserContext.getCurrentUser().getUserId());
        map.addValue("APPROVED_ON", Timestamp.from(Instant.now()));
        jdbcOperations.update(APPROVE_EVENT_TYPE, map);
    }

}
