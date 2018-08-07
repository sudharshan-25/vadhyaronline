package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.authentication.LoginUserContext;
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

    private LoginUserContext loginUserContext;

    public EventCategoryService(EventCategoryRepository eventCategoryRepo, LoginUserContext loginUserContext) {
        this.eventCategoryRepo = eventCategoryRepo;
        this.loginUserContext = loginUserContext;
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
        eventCategoryEntity.setRequestedBy(loginUserContext.getCurrentUser().getUserId());
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
        int approvedBy = loginUserContext.getCurrentUser().getUserId();
        eventCategories.forEach(eventCategory -> {
            eventCategory.setApproved(true);
            eventCategory.setApprovedBy(approvedBy);
        });
        eventCategoryRepo.saveAll(eventCategories);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteEventCategories(List<Integer> eventCategoryIds) {
        eventCategoryIds.forEach(id -> eventCategoryRepo.deleteById(id));
    }

}
