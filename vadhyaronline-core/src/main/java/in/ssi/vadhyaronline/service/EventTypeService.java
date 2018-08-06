package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.EventCategoryRepository;
import in.ssi.vadhyaronline.dao.EventTypeRepository;
import in.ssi.vadhyaronline.domain.EventTypeVO;
import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import in.ssi.vadhyaronline.entity.EventTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EventTypeService {

    private EventTypeRepository eventTypeRepo;

    private EventCategoryRepository eventCategoryRepo;

    public EventTypeService(EventTypeRepository eventTypeRepo, EventCategoryRepository eventCategoryRepo) {
        this.eventTypeRepo = eventTypeRepo;
        this.eventCategoryRepo = eventCategoryRepo;
    }

    @Transactional(readOnly = true)
    public Map<String, List<EventTypeVO>> getAllEventTypes() {
        Map<EventCategoryEntity, List<EventTypeEntity>> objects = eventTypeRepo.findAllByApprovedIsTrue()
                        .stream().collect(Collectors.groupingBy(EventTypeEntity::getEventCategory));
        Map<String, List<EventTypeVO>> returnObject = new LinkedHashMap<>();
        objects.forEach((key, value) ->
                returnObject.put(key.getCategoryName(), value.stream().
                        map(EventTypeEntity::toDomain).collect(Collectors.toList()))
        );
        return returnObject;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addEventType(EventTypeVO eventTypeVO) {
        EventCategoryEntity eventCategoryEntity = eventCategoryRepo.getOne(eventTypeVO.getEventCategoryId());
        EventTypeEntity eventType = new EventTypeEntity();
        eventType.setEventCategory(eventCategoryEntity);
        eventType.setEventTypeName(eventTypeVO.getEventTypeName());
        eventType.setEventTypeDescription(eventTypeVO.getEventTypeDescription());
        eventType.setApproved(false);
        eventTypeRepo.save(eventType);
    }

    @Transactional(readOnly = true)
    public List<EventTypeVO> searchEventTypes(Integer categoryId) {
        EventCategoryEntity eventCategory = new EventCategoryEntity();
        eventCategory.setCategoryId(categoryId);
        return eventTypeRepo.findEventTypeEntitiesByEventCategoryAndApprovedIsTrue(eventCategory).stream()
                .map(EventTypeEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEventType(Integer eventTypeId, EventTypeVO eventTypeVO) {
        EventTypeEntity eventType = eventTypeRepo.getOne(eventTypeId);
        EventCategoryEntity eventCategory = eventCategoryRepo.getOne(eventTypeVO.getEventCategoryId());
        eventType.setEventTypeName(eventTypeVO.getEventTypeName());
        eventType.setEventTypeDescription(eventTypeVO.getEventTypeDescription());
        eventType.setEventCategory(eventCategory);
        eventType.setApproved(eventTypeVO.isApproved());
        eventTypeRepo.save(eventType);
    }


    @Transactional(readOnly = true)
    public List<EventTypeVO> unapprovedEventTypes() {
        return eventTypeRepo.findAllByApprovedIsFalse().stream()
                .map(EventTypeEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void approveEventType(List<EventTypeVO> eventTypes) {
        List<Integer> eventTypeIds = eventTypes.stream().map(EventTypeVO::getEventTypeId).collect(Collectors.toList());
        List<EventTypeEntity> eventTypeEntities = eventTypeRepo.findAllById(eventTypeIds);
        eventTypeEntities.forEach(eventType -> eventType.setApproved(true));
        eventTypeRepo.saveAll(eventTypeEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteEventType(List<Integer> eventTypeIds) {
        eventTypeIds.forEach(id -> eventTypeRepo.deleteById(id));
    }

}
