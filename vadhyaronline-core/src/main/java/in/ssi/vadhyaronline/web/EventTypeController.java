package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.EventTypeVO;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.EventTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/common")
public class EventTypeController {

    private EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @RequestMapping(value = "/eventTypes", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getEventTypes() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            Map<String, List<EventTypeVO>> eventTypes = eventTypeService.getAllEventTypes();
            response.setData(eventTypes);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventTypes", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addEventTypes(@RequestBody EventTypeVO eventTypeVO) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventTypeService.addEventType(eventTypeVO);
            response.setData("Event type added successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventTypesForCategory", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> searchEventTypes(@RequestParam("categoryId") Integer categoryId) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<EventTypeVO> eventTypes = eventTypeService.searchEventTypes(categoryId);
            response.setData(eventTypes);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventTypes/{eventTypeId}", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> updateEventTypes(@PathVariable("eventTypeId") Integer eventTypeId,
                                                            @RequestBody EventTypeVO eventTypeVO) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventTypeService.updateEventType(eventTypeId, eventTypeVO);
            response.setData("Event type updated successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventTypes", method = RequestMethod.DELETE)
    public ResponseEntity<VadhyarResponse> deleteEventType(@RequestBody List<Integer> eventTypeIds) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventTypeService.deleteEventType(eventTypeIds);
            response.setData("Event type(s) deleted successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/unapprovedEventTypes", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUnapprovedEventTypes() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<EventTypeVO> eventTypes = eventTypeService.unapprovedEventTypes();
            response.setData(eventTypes);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/approveEventTypes", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> approvedEventTypes(@RequestBody List<EventTypeVO> eventTypes) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventTypeService.approveEventType(eventTypes);
            response.setData("Event Type(s) approved successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
