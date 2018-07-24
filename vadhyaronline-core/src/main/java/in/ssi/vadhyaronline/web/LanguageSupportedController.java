package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.LanguageSupported;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.LanguageSupportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class LanguageSupportedController {

    @Autowired
    private LanguageSupportedService languageService;

    @RequestMapping(value = "/langSupported", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getSupportedLanguages() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<LanguageSupported> langSupported = languageService.getSupportedLanguages();
            response.setData(langSupported);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/langSupported", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addSupportedLanguages(@RequestBody LanguageSupported languageSupported) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            languageService.addLanguageSupport(languageSupported);
            response.setData("Language Added Successfully");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
