package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.EventCategory;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.EventCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/common")
public class EventCategoryController {

    private EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @RequestMapping(value = "/eventCategory", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getAllEventCategories() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<EventCategory> eventCategories = eventCategoryService.getAllEventCategories();
            response.setData(eventCategories);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventCategory", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addEventCategories(@RequestBody EventCategory eventCategory) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventCategoryService.addEventCategory(eventCategory);
            response.setData("Event category added successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventCategory/{eventCategoryId}", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> updateEventCategory(@PathVariable("eventCategoryId") Integer eventCategoryId,
                                                               @RequestBody EventCategory eventCategory) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventCategoryService.updateEventCategory(eventCategoryId, eventCategory);
            response.setData("Event category added successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/unApprovedEventCategory", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> unapprovedEventCategories() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<EventCategory> eventCategories = eventCategoryService.unapprovedEventCategory();
            response.setData(eventCategories);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/approveEventCategory", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> approveEventCategories(@RequestBody List<EventCategory> eventCategoryIds) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventCategoryService.approveEventCategories(eventCategoryIds);
            response.setData("Event category/s approved successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/deleteEventCategory", method = RequestMethod.DELETE)
    public ResponseEntity<VadhyarResponse> deleteEventCategories(@RequestBody List<Integer> eventCategoryIds) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventCategoryService.deleteEventCategories(eventCategoryIds);
            response.setData("Event category/s deleted successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
