package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.authentication.VOAccessRole;
import in.ssi.vadhyaronline.authentication.VOAccessRoles;
import in.ssi.vadhyaronline.authentication.VOAuthenticated;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.GothramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/master")
@VOAuthenticated
public class MasterGothramController {

    private GothramService gothramService;

    public MasterGothramController(GothramService gothramService) {
        this.gothramService = gothramService;
    }

    @GetMapping(value = "/gothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER})
    public ResponseEntity<VadhyarResponse> getGothrams() {
        VadhyarResponse response = new VadhyarResponse();
        List<AbstractResponse> gothramList = gothramService.getAllGothram();
        response.setData(gothramList);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/gothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> addGothram(@RequestBody AbstractResponse gothram) {
        VadhyarResponse response = new VadhyarResponse();
        gothramService.addGothram(gothram.getValue());
        response.setData("Gothram added successfully...");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/gothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> updateGothram(@RequestBody AbstractResponse gothram) {
        VadhyarResponse response = new VadhyarResponse();
        gothramService.updateGothram(gothram.getId(), gothram.getValue());
        response.setData("Gothram updated successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unapprovedGothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> getUnapprovedGothrams() {
        VadhyarResponse response = new VadhyarResponse();
        List<AbstractResponse> gothramList = gothramService.getUnapprovedGothrams();
        response.setData(gothramList);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/approveGothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> approveGothram(@RequestBody List<AbstractResponse> gothrams) {
        VadhyarResponse response = new VadhyarResponse();
        gothramService.approveGothrams(gothrams);
        response.setData("Gothram(s) approved successfully...");
        return ResponseEntity.ok(response);
    }
}
