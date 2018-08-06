package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.EventCategoryRepository;
import in.ssi.vadhyaronline.domain.EventCategory;
import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EventCategoryService {

    private EventCategoryRepository eventCategoryRepo;

    public EventCategoryService(EventCategoryRepository eventCategoryRepo) {
        this.eventCategoryRepo = eventCategoryRepo;
    }

    @Transactional(readOnly = true)
    public List<EventCategory> getAllEventCategories() {
        return eventCategoryRepo.findEventCategoryEntitiesByApprovedIsTrue()
                .stream().map(EventCategoryEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addEventCategory(EventCategory eventCategory) {
        EventCategoryEntity eventCategoryEntity = new EventCategoryEntity();
        eventCategoryEntity.setCategoryName(eventCategory.getEventCategoryName());
        eventCategoryEntity.setCategoryId(0);
        eventCategoryEntity.setApproved(false);
        eventCategoryRepo.save(eventCategoryEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEventCategory(Integer eventCategoryId, EventCategory eventCategory) {
        EventCategoryEntity eventCategoryEntity = eventCategoryRepo.getOne(eventCategoryId);
        eventCategoryEntity.setCategoryName(eventCategory.getEventCategoryName());
        eventCategoryEntity.setApproved(eventCategory.isApproved());
        eventCategoryRepo.save(eventCategoryEntity);
    }

    @Transactional(readOnly = true)
    public List<EventCategory> unapprovedEventCategory() {
        return eventCategoryRepo.findEventCategoryEntitiesByApprovedIsFalse()
                .stream().map(EventCategoryEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void approveEventCategories(List<EventCategory> eventCats) {
        List<Integer> eventCategoryIds = eventCats.stream().map(EventCategory::getCategoryId).collect(Collectors.toList());
        List<EventCategoryEntity> eventCategories = eventCategoryRepo.findAllById(eventCategoryIds);
        eventCategories.forEach(eventCategory -> eventCategory.setApproved(true));
        eventCategoryRepo.saveAll(eventCategories);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteEventCategories(List<Integer> eventCategoryIds) {
        eventCategoryIds.forEach(id -> eventCategoryRepo.deleteById(id));
    }

}
