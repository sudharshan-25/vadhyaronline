package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.entity.UserRole;
import in.ssi.vadhyaronline.service.UserRoleServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRolesController {

    @Autowired
    private UserRoleServices userRoleServices;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUserRoles() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<UserRole> userRoles = userRoleServices.getUserRoles();
            response.setData(userRoles);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
