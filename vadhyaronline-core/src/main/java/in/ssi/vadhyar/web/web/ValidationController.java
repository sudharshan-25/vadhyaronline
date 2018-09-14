package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.ValidationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/validate")
public class ValidationController {

    private ValidationService validationService;


    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @RequestMapping("/unique/mobile")
    public VOResponse validUniqueMobile(@RequestParam("key") String mobileNumber) {
        VOResponse voResponse = new VOResponse();
        voResponse.setData(validationService.isUniqueMobile(mobileNumber));
        return voResponse;
    }

    @RequestMapping("/unique/email")
    public VOResponse validUniqueEmail(@RequestParam("key") String email) {
        VOResponse voResponse = new VOResponse();
        voResponse.setData(validationService.isUniqueEmail(email));
        return voResponse;
    }


    @RequestMapping("/unique/userName")
    public VOResponse validUniqueUserName(@RequestParam("key") String userName) {
        VOResponse voResponse = new VOResponse();
        voResponse.setData(validationService.isUniqueUserName(userName));
        return voResponse;
    }


}
