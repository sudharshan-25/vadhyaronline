package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.MasterTableService;
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
public class MasterController {

    @Autowired
    private MasterTableService masterTableService;

    @RequestMapping(value = "/gothram", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getGothrams() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> gothramList = masterTableService.getAllGothram();
            response.setData(gothramList);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/gothram", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addGothram(@RequestBody AbstractResponse gothram) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            String gothramName = gothram.getValue();
            if (masterTableService.doesGothramExist(gothramName)) {
                response.setData("Gothram already exists...");
            } else {
                masterTableService.addGothram(gothram.getValue());
                response.setData("Gothram added successfully...");
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/gothram", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> updateGothram(@RequestBody AbstractResponse gothram) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            String gothramName = gothram.getValue();
            if (masterTableService.doesGothramExist(gothramName)) {
                response.setData("Gothram Name already exists...");
            } else {
                masterTableService.updateGothram(gothram.getId(), gothram.getValue());
                response.setData("Gothram updated successfully...");
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/soothram", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getSoothrams() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<AbstractResponse> soothramList = masterTableService.getAllSoothram();
            response.setData(soothramList);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/soothram", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addSoothram(@RequestBody AbstractResponse soothram) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            String soothramName = soothram.getValue();
            if (masterTableService.doesSoothramExist(soothramName)) {
                response.setData("Soothram already exists...");
            } else {
                masterTableService.addSoothram(soothram.getValue());
                response.setData("Soothram added successfully...");
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/soothram", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> updateSoothram(@RequestBody AbstractResponse soothram) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            String soothramName = soothram.getValue();
            if (masterTableService.doesSoothramExist(soothramName)) {
                response.setData("Soothram Name already exists...");
            } else {
                masterTableService.updateSoothram(soothram.getId(), soothram.getValue());
                response.setData("Soothram updated successfully...");
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
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
}
