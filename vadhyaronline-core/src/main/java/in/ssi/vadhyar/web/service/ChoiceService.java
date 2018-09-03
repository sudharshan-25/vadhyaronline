package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.repository.jdbc.EventCategoryJdbcRepository;
import in.ssi.vadhyar.web.repository.jdbc.EventTypeJdbcRepository;
import in.ssi.vadhyar.web.repository.jdbc.RoleJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    @Autowired
    private RoleJdbcRepository roleJdbcRepository;

    @Autowired
    private LoginUserContext loginUserContext;

    @Autowired
    private EventCategoryJdbcRepository eventCategoryJdbcRepository;

    @Autowired
    private EventTypeJdbcRepository eventTypeJdbcRepository;

    public List<DropDownChoice> getRoles() {
        List<DropDownChoice> roles = roleJdbcRepository.getDropDownList();
        if (loginUserContext.getCurrentUser().isAdmin()) {
            roles.remove(RoleJdbcRepository.ADMIN_CHOICE);
        }
        return roles;
    }

    public List<DropDownChoice> getEventCategories() {
        return eventCategoryJdbcRepository.getDropDownList();
    }

    public List<DropDownChoice> getEventTypes() {
        return eventTypeJdbcRepository.getDropDownList();
    }

    public List<DropDownChoice> getEventTypes(Integer categoryId) {
        DropDownChoice dropDownChoice = new DropDownChoice();
        dropDownChoice.setCriteriaId(categoryId);
        return eventTypeJdbcRepository.getDropDownListForCriteria(dropDownChoice);
    }

}
