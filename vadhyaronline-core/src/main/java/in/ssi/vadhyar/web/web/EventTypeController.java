package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAccessRole;
import in.ssi.vadhyar.web.authentication.VOAccessRoles;
import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.EventType;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.EventTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("eventType")
@VOAuthenticated
public class EventTypeController {

    private EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping("/")
    public List<EventType> getEventTypes() {
        return eventTypeService.getAllApproved();
    }

    @GetMapping("/all")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public List<EventType> getApprovedEventTypes() {
        return eventTypeService.getAllEventTypes();
    }

    @GetMapping("/unapproved")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public List<EventType> getUnApprovedEventTypes() {
        return eventTypeService.getAllUnApproved();
    }

    @GetMapping("/requested")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getRequested() {
        VOResponse response = new VOResponse();
        response.setData(eventTypeService.getAllRequested());
        return response;
    }

    @PostMapping("/")
    public VOResponse createEventType(@RequestBody EventType eventType) {
        VOResponse response = new VOResponse();
        eventTypeService.createEventType(eventType);
        response.setData("Event Type created successfully");
        return response;
    }

    @GetMapping("/{eventTypeId}")
    public VOResponse getEventType(@PathVariable Integer eventTypeId) {
        VOResponse response = new VOResponse();
        response.setData(eventTypeService.getEventType(eventTypeId));
        return response;
    }

    @PutMapping("/{eventTypeId}")
    public VOResponse updateEventType(@PathVariable Integer eventTypeId, @RequestBody EventType eventType) {
        VOResponse response = new VOResponse();
        eventType.setEventTypeId(eventTypeId);
        eventTypeService.updateEventType(eventType);
        response.setData("Event Type updated successfully");
        return response;
    }

    @PutMapping("/{eventTypeId}/approve")
    public VOResponse updateEventType(@PathVariable Integer eventTypeId) {
        VOResponse response = new VOResponse();
        eventTypeService.approveEventType(eventTypeId);
        response.setData("Event Type approved successfully");
        return response;
    }

    @DeleteMapping("/{eventTypeId}")
    public VOResponse deleteEventType(@PathVariable Integer eventTypeId) {
        VOResponse response = new VOResponse();
        eventTypeService.deleteEventType(eventTypeId);
        response.setData("Event Type approved successfully");
        return response;
    }
}
