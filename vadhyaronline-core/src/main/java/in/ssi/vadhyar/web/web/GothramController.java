package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAccessRole;
import in.ssi.vadhyar.web.authentication.VOAccessRoles;
import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.Gothram;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.GothramService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gothram")
@VOAuthenticated
public class GothramController {

    private GothramService gothramService;

    public GothramController(GothramService gothramService) {
        this.gothramService = gothramService;
    }

    @GetMapping("/")
    public VOResponse getGothrams() {
        VOResponse response = new VOResponse();
        response.setData(gothramService.findAllApproved());
        return response;
    }

    @GetMapping("/all")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getAllGothrams() {
        VOResponse response = new VOResponse();
        response.setData(gothramService.findAllGothrams());
        return response;
    }

    @GetMapping("/unapproved")
    @VOAccessRoles(accessRoles = VOAccessRole.ADMIN)
    public VOResponse getUnapprovedGothrams() {
        VOResponse response = new VOResponse();
        response.setData(gothramService.findAllUnApproved());
        return response;
    }

    @GetMapping("/requested")
    @VOAccessRoles(accessRoles = {VOAccessRole.VADHYAR, VOAccessRole.USER})
    public VOResponse getRequestedGothrams() {
        VOResponse response = new VOResponse();
        response.setData(gothramService.findAllRequested());
        return response;
    }

    @PostMapping("/")
    public VOResponse createGothram(@RequestBody Gothram gothram) {
        VOResponse response = new VOResponse();
        gothramService.createGothram(gothram);
        response.setData("Gothram created successfully");
        return response;
    }

    @GetMapping("/{gothramId}")
    public VOResponse getSoothram(@PathVariable Integer gothramId) {
        VOResponse response = new VOResponse();
        response.setData(gothramService.findGothram(gothramId));
        return response;
    }

    @PutMapping("/{gothramId}")
    public VOResponse updateGothram(@PathVariable Integer gothramId, @RequestBody Gothram gothram) {
        VOResponse response = new VOResponse();
        gothram.setGothramId(gothramId);
        gothramService.updateGothram(gothram);
        response.setData("Gothram updated successfully");
        return response;
    }

    @PutMapping("/{gothramId}/approve")
    public VOResponse approveGothram(@PathVariable Integer gothramId) {
        VOResponse response = new VOResponse();
        gothramService.approveGothram(gothramId);
        response.setData("Gothram approved successfully");
        return response;
    }

    @DeleteMapping("/{gothramId}")
    public VOResponse deleteGothram(@PathVariable Integer gothramId) {
        VOResponse response = new VOResponse();
        gothramService.deleteGothram(gothramId);
        response.setData("Gothram deleted successfully");
        return response;
    }

}
