package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.authentication.VOAccessRole;
import in.ssi.vadhyaronline.authentication.VOAccessRoles;
import in.ssi.vadhyaronline.authentication.VOAuthenticated;
import in.ssi.vadhyaronline.domain.Gotharam;
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
        response.setData(gothramService.getAllGothram());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/gothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> addGothram(@RequestBody Gotharam gothram) {
        VadhyarResponse response = new VadhyarResponse();
        gothramService.addGothram(gothram);
        response.setData("Gothram added successfully...");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/gothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> updateGothram(@RequestBody Gotharam gothram) {
        VadhyarResponse response = new VadhyarResponse();
        gothramService.updateGothram(gothram.getGothramId(), gothram.getGothramName());
        response.setData("Gothram updated successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unapprovedGothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> getUnapprovedGothrams() {
        VadhyarResponse response = new VadhyarResponse();
        response.setData(gothramService.getUnapprovedGothrams());
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/approveGothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> approveGothram(@RequestBody List<Gotharam> gothrams) {
        VadhyarResponse response = new VadhyarResponse();
        gothramService.approveGothrams(gothrams);
        response.setData("Gothram(s) approved successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/requestedGothrams")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> getRequestedGothrams() {
        VadhyarResponse response = new VadhyarResponse();
        response.setData(gothramService.getRequestedByGothrams());
        return ResponseEntity.ok(response);
    }

}
