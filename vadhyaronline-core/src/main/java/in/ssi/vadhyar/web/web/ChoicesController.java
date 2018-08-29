package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/choices")
@VOAuthenticated
public class ChoicesController {

    @Autowired
    private ChoiceService choiceService;

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

}
