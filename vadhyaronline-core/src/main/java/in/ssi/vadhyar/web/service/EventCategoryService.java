package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.EventCategory;
import in.ssi.vadhyar.web.entity.EventCategoryEntity;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.jdbc.EventCategoryJdbcRepository;
import in.ssi.vadhyar.web.repository.jpa.EventCategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventCategoryService {

    @Autowired
    private LoginUserContext loginContext;

    @Autowired
    private EventCategoryJpaRepository eventCategoryJpaRepository;

    @Autowired
    private EventCategoryJdbcRepository eventCategoryJdbcRepository;

    public List<EventCategory> getAllEventCategories() {
        return eventCategoryJpaRepository.findAll().stream().map(EventCategoryEntity::toDomain)
                .collect(Collectors.toList());
    }

    public List<EventCategory> getApprovedEventCategories(boolean approvedStatus) {
        return eventCategoryJpaRepository.findByApproved(approvedStatus).stream()
                .map(EventCategoryEntity::toDomain).collect(Collectors.toList());
    }

    public void createEventCategory(EventCategory eventCategory) {
        if (eventCategoryJdbcRepository.isExistsByKey(eventCategory.getEventCategoryName()))
            throw new VadhyarOnlineException("Category Name Already exists");
        EventCategoryEntity categoryEntity = new EventCategoryEntity();
        categoryEntity.setEventCategoryName(eventCategory.getEventCategoryName());
        categoryEntity.setRequestedOn(Timestamp.from(Instant.now()));
        categoryEntity.setRequestedBy(loginContext.getCurrentUser().getUserId());
        eventCategoryJpaRepository.save(categoryEntity);
    }


    public void updateEventCategory(EventCategory eventCategory) {
        if (eventCategoryJdbcRepository.isExistsByKey(eventCategory.getEventCategoryName()))
            throw new VadhyarOnlineException("Category Name Already exists");
        eventCategoryJdbcRepository.updateEventCategory(eventCategory);
    }

    public void approveEventCategory(Integer eventCategoryId) {
        eventCategoryJdbcRepository.approveEventCategory(eventCategoryId);
    }

    public void deleteEventCategory(Integer eventCategoryId) {
        eventCategoryJpaRepository.deleteById(eventCategoryId);
    }

}
