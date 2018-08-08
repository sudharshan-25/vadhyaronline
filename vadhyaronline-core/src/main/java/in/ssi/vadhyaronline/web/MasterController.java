package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.MasterTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        VadhyarResponse response = new VadhyarResponse();
        List<AbstractResponse> vedaList = masterTableService.getAllVeda();
        response.setData(vedaList);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/userRoles", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUserRoleList() {
        VadhyarResponse response = new VadhyarResponse();
        List<AbstractResponse> userRolesList = masterTableService.getAllRoles();
        response.setData(userRolesList);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/userStatus", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUserStatusList() {
        VadhyarResponse response = new VadhyarResponse();
        List<AbstractResponse> userStatusList = masterTableService.getAllStatusMaster();
        response.setData(userStatusList);
        return ResponseEntity.ok(response);
    }
}
