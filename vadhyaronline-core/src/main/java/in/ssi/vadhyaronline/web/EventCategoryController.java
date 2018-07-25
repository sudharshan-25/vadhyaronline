package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class EventCategoryController {

    @Autowired
    private EventCategoryService eventCategoryService;

    @RequestMapping(value = "/eventCategory", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getAllEventCategories() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> eventCategories = eventCategoryService.getAllEventCategories();
            response.setData(eventCategories);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/eventCategory", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addEventCategories(@RequestBody AbstractResponse abstractResponse) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            eventCategoryService.addEventCategory(abstractResponse);
            response.setData("Event category added successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/searchEventCategory", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> searchEventCategories(@RequestParam("name") String name) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> eventCategories = eventCategoryService.searchEventCategory(name);
            response.setData(eventCategories);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
