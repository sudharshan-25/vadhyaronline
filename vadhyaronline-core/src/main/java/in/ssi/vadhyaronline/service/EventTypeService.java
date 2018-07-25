package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.EventCategoryRepository;
import in.ssi.vadhyaronline.dao.EventTypeRepository;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.domain.EventTypeVO;
import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import in.ssi.vadhyaronline.entity.EventTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EventTypeService {

    @Autowired
    private EventTypeRepository eventTypeRepo;

    @Autowired
    private EventCategoryRepository eventCategoryRepo;

    @Transactional(readOnly = true)
    public Map<String, List<EventTypeVO>> getAllEventTypes() {
        Map<EventCategoryEntity, List<EventTypeEntity>> objects =
                eventTypeRepo.findAll().stream().collect(Collectors.groupingBy(EventTypeEntity::getEventCategory));
        Map<String, List<EventTypeVO>> returnObject = new LinkedHashMap<>();
        objects.forEach((key, value) -> {
            returnObject.put(key.getCategoryName(), value.stream().map(elem ->
                    new EventTypeVO(elem.getEventTypeId(), key.getCategoryId(), key.getCategoryName(),
                            elem.getEventTypeName(), elem.getEventTypeDescription())
            ).collect(Collectors.toList()));
        });
        return returnObject;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addEventType(EventTypeVO eventTypeVO) {
        EventCategoryEntity eventCategoryEntity = eventCategoryRepo.getOne(eventTypeVO.getEventCategoryId());
        EventTypeEntity eventType = new EventTypeEntity();
        eventType.setEventCategory(eventCategoryEntity);
        eventType.setEventTypeName(eventTypeVO.getEventTypeName());
        eventType.setEventTypeDescription(eventTypeVO.getEventTypeDescription());
        eventTypeRepo.save(eventType);
    }

    @Transactional(readOnly = true)
    public List<EventTypeVO> searchEventTypes(String categoryName) {
        EventCategoryEntity eventCategory = eventCategoryRepo
                .findEventCategoryEntitiesByCategoryName(categoryName).get(0);
        return eventTypeRepo.findEventTypeEntitiesByEventCategory(eventCategory).stream()
                .map(eventType -> new EventTypeVO(eventType.getEventTypeId(),
                        eventType.getEventCategory().getCategoryId(), eventType.getEventCategory().getCategoryName(),
                        eventType.getEventTypeName(), eventType.getEventTypeDescription())).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEventType(Integer eventTypeId, EventTypeVO eventTypeVO) {
        EventTypeEntity eventType = eventTypeRepo.getOne(eventTypeId);
        EventCategoryEntity eventCategory = eventCategoryRepo.getOne(eventTypeVO.getEventCategoryId());
        eventType.setEventTypeName(eventTypeVO.getEventTypeName());
        eventType.setEventTypeDescription(eventTypeVO.getEventTypeDescription());
        eventType.setEventCategory(eventCategory);
        eventTypeRepo.save(eventType);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteEventType(Integer eventTypeId) {
        EventTypeEntity eventType = eventTypeRepo.getOne(eventTypeId);
        eventTypeRepo.delete(eventType);
    }

}
