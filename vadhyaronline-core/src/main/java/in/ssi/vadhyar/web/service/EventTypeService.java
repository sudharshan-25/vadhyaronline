package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.EventType;
import in.ssi.vadhyar.web.entity.EventCategoryEntity;
import in.ssi.vadhyar.web.entity.EventTypeEntity;
import in.ssi.vadhyar.web.entity.SimpleUserEntity;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.jdbc.EventTypeJdbcRepository;
import in.ssi.vadhyar.web.repository.jpa.EventTypeJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventTypeService {

    private LoginUserContext loginContext;

    private EventTypeJpaRepository eventTypeJpaRepository;

    private EventTypeJdbcRepository eventTypeJdbcRepository;

    public EventTypeService(LoginUserContext loginContext, EventTypeJpaRepository eventTypeJpaRepository,
                            EventTypeJdbcRepository eventTypeJdbcRepository) {
        this.loginContext = loginContext;
        this.eventTypeJpaRepository = eventTypeJpaRepository;
        this.eventTypeJdbcRepository = eventTypeJdbcRepository;
    }

    public List<EventType> getAllEventTypes() {
        return eventTypeJpaRepository.findAll().stream().map(EventTypeEntity::toDomain).collect(Collectors.toList());
    }

    public List<EventType> getAllApproved() {
        return eventTypeJpaRepository.findAllByApproved(Boolean.TRUE).stream()
                .map(EventTypeEntity::toDomain).collect(Collectors.toList());
    }

    public List<EventType> getAllUnApproved() {
        return eventTypeJpaRepository.findAllByApproved(Boolean.FALSE).stream()
                .map(EventTypeEntity::toDomain).collect(Collectors.toList());
    }

    public List<EventType> getAllRequested() {
        return eventTypeJpaRepository.findByRequestedBy(SimpleUserEntity.of(loginContext.getCurrentUser().getUserId()))
                .stream().map(EventTypeEntity::toDomain).collect(Collectors.toList());
    }

    public EventType getEventType(Integer eventTypeId) {
        return eventTypeJpaRepository.getOne(eventTypeId).toDomain();
    }

    public void createEventType(EventType eventType) {
        if (eventTypeJdbcRepository.isExistsByKey(eventType.getEventTypeName()))
            throw new VadhyarOnlineException("Event Type name already exists");

        EventCategoryEntity categoryEntity = new EventCategoryEntity();
        categoryEntity.setEventCategoryId(eventType.getEventCategoryId());

        EventTypeEntity eventTypeEntity = new EventTypeEntity();
        eventTypeEntity.setEventCategory(categoryEntity);
        eventTypeEntity.setEventTypeDescription(eventType.getEventTypeDescription());
        eventTypeEntity.setEventTypeName(eventType.getEventTypeName());
        eventTypeEntity.setRequestedBy(SimpleUserEntity.of(loginContext.getCurrentUser().getUserId()));
        eventTypeEntity.setRequestedOn(Timestamp.from(Instant.now()));
        eventTypeJpaRepository.save(eventTypeEntity);
    }

    public void updateEventType(EventType eventType) {
        if (eventTypeJdbcRepository.isExistsByKey(eventType.getEventTypeName()))
            throw new VadhyarOnlineException("Event Type name already exists");
        eventTypeJdbcRepository.updateEventType(eventType);
    }


    public void approveEventType(int eventTypeId) {
        eventTypeJdbcRepository.approveEventType(eventTypeId);
    }

    public void deleteEventType(int eventTypeId) {
        if (eventTypeJdbcRepository.canDelete(eventTypeId)) {
            eventTypeJpaRepository.deleteById(eventTypeId);
            return;
        }
        throw new VadhyarOnlineException("Links already exists. Cannot delete Event Type");
    }
}
