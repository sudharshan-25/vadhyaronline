package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.ChoiceService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/vedas")
    public VOResponse getVedas() {
        VOResponse response = new VOResponse();
        response.setData(choiceService.getVedas());
        return response;
    }

    @GetMapping("/soothram")
    public VOResponse getSoothrams() {
        VOResponse response = new VOResponse();
        response.setData(choiceService.getSoothrams());
        return response;
    }

    @GetMapping("/gothram")
    public VOResponse getGothrams() {
        VOResponse response = new VOResponse();
        response.setData(choiceService.getGothrams());
        return response;
    }

}
