package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.authentication.VOAccessRole;
import in.ssi.vadhyaronline.authentication.VOAccessRoles;
import in.ssi.vadhyaronline.authentication.VOAuthenticated;
import in.ssi.vadhyaronline.domain.EventTypeVO;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.EventTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/master")
@VOAuthenticated
public class EventTypeController {

    private EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping(value = "/eventTypes")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER})
    public ResponseEntity<VadhyarResponse> getEventTypes() {
        VadhyarResponse response = new VadhyarResponse();
        Map<String, List<EventTypeVO>> eventTypes = eventTypeService.getAllEventTypes();
        response.setData(eventTypes);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/eventTypes")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> addEventTypes(@RequestBody EventTypeVO eventTypeVO) {
        VadhyarResponse response = new VadhyarResponse();
        eventTypeService.addEventType(eventTypeVO);
        response.setData("Event type added successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/eventTypesForCategory")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER})
    public ResponseEntity<VadhyarResponse> searchEventTypes(@RequestParam("categoryId") Integer categoryId) {
        VadhyarResponse response = new VadhyarResponse();
        List<EventTypeVO> eventTypes = eventTypeService.searchEventTypes(categoryId);
        response.setData(eventTypes);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/eventTypes/{eventTypeId}")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> updateEventTypes(@PathVariable("eventTypeId") Integer eventTypeId,
                                                            @RequestBody EventTypeVO eventTypeVO) {
        VadhyarResponse response = new VadhyarResponse();
        eventTypeService.updateEventType(eventTypeId, eventTypeVO);
        response.setData("Event type updated successfully...");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/eventTypes")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> deleteEventType(@RequestBody List<Integer> eventTypeIds) {
        VadhyarResponse response = new VadhyarResponse();
        eventTypeService.deleteEventType(eventTypeIds);
        response.setData("Event type(s) deleted successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unapprovedEventTypes")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public ResponseEntity<VadhyarResponse> getUnapprovedEventTypes() {
        VadhyarResponse response = new VadhyarResponse();
        List<EventTypeVO> eventTypes = eventTypeService.unapprovedEventTypes();
        response.setData(eventTypes);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/approveEventTypes")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public ResponseEntity<VadhyarResponse> approvedEventTypes(@RequestBody List<EventTypeVO> eventTypes) {
        VadhyarResponse response = new VadhyarResponse();
        eventTypeService.approveEventType(eventTypes);
        response.setData("Event Type(s) approved successfully...");
        return ResponseEntity.ok(response);
    }
}
