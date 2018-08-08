package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.authentication.VOAccessRole;
import in.ssi.vadhyaronline.authentication.VOAccessRoles;
import in.ssi.vadhyaronline.authentication.VOAuthenticated;
import in.ssi.vadhyaronline.domain.EventCategory;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.EventCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/master")
@VOAuthenticated
public class EventCategoryController {

    private EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @GetMapping(value = "/eventCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER})
    public ResponseEntity<VadhyarResponse> getAllEventCategories() {
        VadhyarResponse response = new VadhyarResponse();
        List<EventCategory> eventCategories = eventCategoryService.getAllEventCategories();
        response.setData(eventCategories);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/eventCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> addEventCategories(@RequestBody EventCategory eventCategory) {
        VadhyarResponse response = new VadhyarResponse();
        eventCategoryService.addEventCategory(eventCategory);
        response.setData("Event category added successfully...");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/eventCategory/{eventCategoryId}")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> updateEventCategory(@PathVariable("eventCategoryId") Integer eventCategoryId,
                                                               @RequestBody EventCategory eventCategory) {
        VadhyarResponse response = new VadhyarResponse();
        eventCategoryService.updateEventCategory(eventCategoryId, eventCategory);
        response.setData("Event category added successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unApprovedEventCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> unapprovedEventCategories() {
        VadhyarResponse response = new VadhyarResponse();
        List<EventCategory> eventCategories = eventCategoryService.unapprovedEventCategory();
        response.setData(eventCategories);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/approveEventCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> approveEventCategories(@RequestBody List<EventCategory> eventCategoryIds) {
        VadhyarResponse response = new VadhyarResponse();
        eventCategoryService.approveEventCategories(eventCategoryIds);
        response.setData("Event category/s approved successfully...");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/deleteEventCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> deleteEventCategories(@RequestBody List<Integer> eventCategoryIds) {
        VadhyarResponse response = new VadhyarResponse();
        eventCategoryService.deleteEventCategories(eventCategoryIds);
        response.setData("Event category/s deleted successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/requestedEventCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> getRequestedEventCategories() {
        VadhyarResponse response = new VadhyarResponse();
        List<EventCategory> eventCategories = eventCategoryService.getRequestedEventCategories();
        response.setData(eventCategories);
        return ResponseEntity.ok(response);
    }

}
