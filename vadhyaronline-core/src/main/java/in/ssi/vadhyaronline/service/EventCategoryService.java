package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.EventCategoryRepository;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EventCategoryService {

    @Autowired
    private EventCategoryRepository eventCategoryRepo;

    @Transactional(readOnly = true)
    public List<AbstractResponse> getAllEventCategories() {
        return eventCategoryRepo.findAll().stream().map(EventCategoryEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addEventCategory(AbstractResponse eventCategory) {
        EventCategoryEntity eventCategoryEntity = new EventCategoryEntity();
        eventCategoryEntity.setCategoryName(eventCategory.getValue());
        eventCategoryEntity.setCategoryId(eventCategory.getId());
        eventCategoryRepo.save(eventCategoryEntity);
    }

    @Transactional(readOnly = true)
    public List<AbstractResponse> searchEventCategory(String categoryName) {
        return eventCategoryRepo.findEventCategoryEntitiesByCategoryName(categoryName)
                .stream().map(EventCategoryEntity::toDomain).collect(Collectors.toList());
    }
}
