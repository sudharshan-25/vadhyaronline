package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAccessRole;
import in.ssi.vadhyar.web.authentication.VOAccessRoles;
import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.Soothram;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.SoothramService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/soothram")
@VOAuthenticated
public class SoothramController {

    private SoothramService soothramService;

    public SoothramController(SoothramService soothramService) {
        this.soothramService = soothramService;
    }

    @GetMapping("/")
    public VOResponse getSoothrams() {
        VOResponse response = new VOResponse();
        response.setData(soothramService.findAllApproved());
        return response;
    }

    @GetMapping("/all")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getAllSoothrams() {
        VOResponse response = new VOResponse();
        response.setData(soothramService.findAllSoothrams());
        return response;
    }

    @GetMapping("/unapproved")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getUnapprovedSoothram() {
        VOResponse response = new VOResponse();
        response.setData(soothramService.findAllUnApproved());
        return response;
    }

    @GetMapping("/requested")
    @VOAccessRoles(accessRoles = {VOAccessRole.VADHYAR, VOAccessRole.USER})
    public VOResponse getRequestedSoothrams() {
        VOResponse response = new VOResponse();
        response.setData(soothramService.findAllRequested());
        return response;
    }

    @PostMapping("/")
    public VOResponse createSoothram(@RequestBody Soothram soothram) {
        VOResponse response = new VOResponse();
        soothramService.createSoothram(soothram);
        response.setData("Soothram created successfully");
        return response;
    }

    @GetMapping("/{soothramId}")
    public VOResponse getSoothram(@PathVariable Integer soothramId) {
        VOResponse response = new VOResponse();
        response.setData(soothramService.findSoothram(soothramId));
        return response;
    }

    @PutMapping("/{soothramId}")
    public VOResponse updateSoothram(@PathVariable Integer soothramId, @RequestBody Soothram soothram) {
        VOResponse response = new VOResponse();
        soothram.setSoothramId(soothramId);
        soothramService.updateSoothram(soothram);
        response.setData("Soothram updated successfully");
        return response;
    }

    @PutMapping("/{soothramId}/approve")
    public VOResponse approveSoothram(@PathVariable Integer soothramId) {
        VOResponse response = new VOResponse();
        soothramService.approveSoothram(soothramId);
        response.setData("Soothram approved successfully");
        return response;
    }

    @DeleteMapping("/{soothramId}")
    public VOResponse deleteSoothram(@PathVariable Integer soothramId) {
        VOResponse response = new VOResponse();
        soothramService.deleteSoothram(soothramId);
        response.setData("Soothram deleted successfully");
        return response;
    }

}
