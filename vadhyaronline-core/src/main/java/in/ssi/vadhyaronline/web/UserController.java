package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.authentication.VOAccessRole;
import in.ssi.vadhyaronline.authentication.VOAccessRoles;
import in.ssi.vadhyaronline.authentication.VOAuthenticated;
import in.ssi.vadhyaronline.domain.UserDomain;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.UserMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@VOAuthenticated
public class UserController {

    private UserMasterService userMasterService;

    public UserController(UserMasterService userMasterService) {
        this.userMasterService = userMasterService;
    }

    @GetMapping(value = "/")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> listUsers() {
        VadhyarResponse response = new VadhyarResponse();
        List<UserDomain> users = userMasterService.getAllUsers();
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{userId}")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR})
    public ResponseEntity<VadhyarResponse> getUserById(@PathVariable("userId") Integer userId) {
        VadhyarResponse response = new VadhyarResponse();
        UserDomain user = userMasterService.getUserById(userId);
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{userId}")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER})
    public ResponseEntity<VadhyarResponse> updateUserById(@PathVariable("userId") Integer userId,
                                                          @RequestBody UserDomain userDomain) {
        VadhyarResponse response = new VadhyarResponse();
        userDomain.setUserId(userId);
        userMasterService.updateUser(userDomain);
        response.setData("User updated successfully...");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{userId}/inactivate")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> inactivateUser(@PathVariable("userId") Integer userId) {
        VadhyarResponse response = new VadhyarResponse();
        userMasterService.inactivateUser(userId);
        response.setData("User inactivated successfully...");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{userId}/activate")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> activateUser(@PathVariable("userId") Integer userId) {
        VadhyarResponse response = new VadhyarResponse();
        userMasterService.activateUser(userId);
        response.setData("User activated successfully...");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{userId}")
    @VOAccessRoles(accessRoles = {VOAccessRole.ADMIN})
    public ResponseEntity<VadhyarResponse> deleteUserById(@PathVariable("userId") Integer userId) {
        VadhyarResponse response = new VadhyarResponse();
        userMasterService.deleteUser(userId);
        response.setData("User deleted successfully...");
        return ResponseEntity.ok(response);
    }

}
