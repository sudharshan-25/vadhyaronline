package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAccessRole;
import in.ssi.vadhyar.web.authentication.VOAccessRoles;
import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.EventCategory;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventCategory")
@VOAuthenticated
public class EventCategoryController {

    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping("/")
    public VOResponse getEventCategories() {
        VOResponse response = new VOResponse();
        response.setData(eventCategoryService.getApprovedEventCategories(Boolean.TRUE));
        return response;
    }

    @GetMapping("/all")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getAllEventCategories() {
        VOResponse response = new VOResponse();
        response.setData(eventCategoryService.getAllEventCategories());
        return response;
    }

    @GetMapping("/unapproved")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getUnapprovedEventCategories() {
        VOResponse response = new VOResponse();
        response.setData(eventCategoryService.getApprovedEventCategories(Boolean.FALSE));
        return response;
    }

    @GetMapping("/requested")
    @VOAccessRoles(accessRoles = {VOAccessRole.VADHYAR, VOAccessRole.USER})
    public VOResponse getRequestedEventCategories() {
        VOResponse response = new VOResponse();
        response.setData(eventCategoryService.getMyRequestedEventCategories());
        return response;
    }

    @PostMapping("/")
    public VOResponse createEventCategory(@RequestBody EventCategory eventCategory) {
        VOResponse response = new VOResponse();
        eventCategoryService.createEventCategory(eventCategory);
        response.setData("Event Category created successfully");
        return response;
    }

    @PutMapping("/{eventCategoryId}")
    public VOResponse updateEventCategory(@PathVariable Integer eventCategoryId, @RequestBody EventCategory eventCategory) {
        VOResponse response = new VOResponse();
        eventCategory.setEventCategoryId(eventCategoryId);
        eventCategoryService.updateEventCategory(eventCategory);
        response.setData("Event Category updated successfully");
        return response;
    }

    @PutMapping("/{eventCategoryId}/approve")
    public VOResponse approveEventCategory(@PathVariable Integer eventCategoryId) {
        VOResponse response = new VOResponse();
        eventCategoryService.approveEventCategory(eventCategoryId);
        response.setData("Event Category approved successfully");
        return response;
    }

    @DeleteMapping("/{eventCategoryId}")
    public VOResponse deleteEventCategory(@PathVariable Integer eventCategoryId) {
        VOResponse response = new VOResponse();
        eventCategoryService.deleteEventCategory(eventCategoryId);
        response.setData("Event Category deleted successfully");
        return response;
    }

}
