package in.ssi.vadhyar.web.repository.jdbc;

import in.ssi.vadhyar.web.domain.DropDownChoice;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface IJdbcRepository {

    List<DropDownChoice> getDropDownList();

    List<DropDownChoice> getDropDownListForCriteria(DropDownChoice criteriaId);

    RowMapper<DropDownChoice> getDropDownMapper();

    boolean isExistsByKey(String key);

    boolean canDelete(Integer id);

}
