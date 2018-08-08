package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.MasterTableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/master")
public class MasterController {

    private MasterTableService masterTableService;

    public MasterController(MasterTableService masterTableService) {
        this.masterTableService = masterTableService;
    }

    @RequestMapping(value = "/veda", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getVedas() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> vedaList = masterTableService.getAllVeda();
            response.setData(vedaList);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userRoles", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUserRoleList() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> userRolesList = masterTableService.getAllRoles();
            response.setData(userRolesList);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userStatus", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUserStatusList() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> userStatusList = masterTableService.getAllStatusMaster();
            response.setData(userStatusList);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
