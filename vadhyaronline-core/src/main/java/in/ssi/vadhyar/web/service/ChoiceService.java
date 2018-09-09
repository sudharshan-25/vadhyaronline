package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.DropDownChoice;
import in.ssi.vadhyar.web.repository.jdbc.*;
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

    @Autowired
    private VedaMasterJdbcRepository vedaMasterJdbcRepository;

    @Autowired
    private SoothramJdbcRepository soothramJdbcRepository;

    @Autowired
    private GothramJdbcRepository gothramJdbcRepository;

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

    public List<DropDownChoice> getVedas() {
        return vedaMasterJdbcRepository.getDropDownList();
    }

    public List<DropDownChoice> getSoothrams() {
        return soothramJdbcRepository.getDropDownList();
    }

    public List<DropDownChoice> getGothrams() {
        return gothramJdbcRepository.getDropDownList();
    }

}
