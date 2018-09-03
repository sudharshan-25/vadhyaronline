package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/choices")
@VOAuthenticated
public class ChoicesController {

    private ChoiceService choiceService;

    public ChoicesController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping("/role")
    public VOResponse getRoles() {
        VOResponse response = new VOResponse();
        response.setData(choiceService.getRoles());
        return response;
    }

    @GetMapping("/eventCategory")
    public VOResponse getEventCategories() {
        VOResponse response = new VOResponse();
        response.setData(choiceService.getEventCategories());
        return response;
    }

    @GetMapping("/eventType")
    public VOResponse getEventCategories(@RequestParam(value = "category", defaultValue = "0") Integer categoryId) {
        VOResponse response = new VOResponse();
        if (categoryId == 0) {
            response.setData(choiceService.getEventTypes());
        } else {
            response.setData(choiceService.getEventTypes(categoryId));
        }

        return response;
    }

}
