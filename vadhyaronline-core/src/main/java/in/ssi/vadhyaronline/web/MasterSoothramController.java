package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.authentication.VOAccessRole;
import in.ssi.vadhyaronline.authentication.VOAccessRoles;
import in.ssi.vadhyaronline.authentication.VOAuthenticated;
import in.ssi.vadhyaronline.domain.Soothram;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.SoothramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/master")
@VOAuthenticated
public class MasterSoothramController {

    private SoothramService soothramService;

    public MasterSoothramController(SoothramService soothramService) {
        this.soothramService = soothramService;
    }

    @GetMapping(value = "/soothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER})
    public ResponseEntity<VadhyarResponse> getSoothrams() {
        VadhyarResponse response = new VadhyarResponse();
        response.setData(soothramService.getAllSoothram());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/soothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.VADHYAR, VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> addSoothram(@RequestBody Soothram soothram) {
        VadhyarResponse response = new VadhyarResponse();
        String soothramName = soothram.getSoothramName();
        if (soothramService.doesSoothramExist(soothramName)) {
            response.setData("Soothram already exists...");
        } else {
            soothramService.addSoothram(soothram);
            response.setData("Soothram added successfully...");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/soothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.VADHYAR, VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> updateSoothram(@RequestBody Soothram soothram) {
        VadhyarResponse response = new VadhyarResponse();
        String soothramName = soothram.getSoothramName();
        if (soothramService.doesSoothramExist(soothramName)) {
            response.setData("Soothram Name already exists...");
        } else {
            soothramService.updateSoothram(soothram.getSoothramId(), soothram.getSoothramName());
            response.setData("Soothram updated successfully...");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unapprovedSoothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> getUnapprovedSoothrams() {
        VadhyarResponse response = new VadhyarResponse();
        response.setData(soothramService.unApprovedSoothrams());
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/approveSoothram")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> approveSoothram(@RequestBody List<Soothram> soothrams) {
        VadhyarResponse response = new VadhyarResponse();
        soothramService.approveSoothrams(soothrams);
        response.setData("Soothram(s) approved successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/requestedSoothrams")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> getRequestedSoothrams() {
        VadhyarResponse response = new VadhyarResponse();
        response.setData(soothramService.getRequestedBySoothrams());
        return ResponseEntity.ok(response);
    }

}
