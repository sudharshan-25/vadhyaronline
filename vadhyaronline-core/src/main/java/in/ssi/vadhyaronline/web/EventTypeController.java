package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.EventTypeVO;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

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

    @RequestMapping(value = "/searchEventTypes", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> searchEventTypes(@RequestParam("categoryName") String categoryName) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<EventTypeVO> eventTypes = eventTypeService.searchEventTypes(categoryName);
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

    @RequestMapping(value = "/eventTypes/{eventTypeId}", method = RequestMethod.DELETE)
    public ResponseEntity<VadhyarResponse> deleteEventType(@PathVariable("eventTypeId") Integer eventTypeId) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventTypeService.deleteEventType(eventTypeId);
            response.setData("Event type deleted successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

}
