package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.domain.UserDomain;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.UserMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserMasterService userMasterService;

    public UserController(UserMasterService userMasterService) {
        this.userMasterService = userMasterService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> listUsers() {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            List<UserDomain> users = userMasterService.getAllUsers();
            response.setData(users);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<VadhyarResponse> addUser(@RequestBody UserDomain userDomain) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            userMasterService.addUser(userDomain);
            response.setData("User Added successfully");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> getUserById(@PathVariable("userId") Integer userId) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            UserDomain user = userMasterService.getUserById(userId);
            response.setData(user);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> updateUserById(@PathVariable("userId") Integer userId,
                                                          @RequestBody UserDomain userDomain) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            userDomain.setUserId(userId);
            userMasterService.updateUser(userDomain);
            response.setData("User updated successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{userId}/inactivate", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> inactivateUser(@PathVariable("userId") Integer userId) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            userMasterService.inactivateUser(userId);
            response.setData("User inactivated successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{userId}/activate", method = RequestMethod.PUT)
    public ResponseEntity<VadhyarResponse> activateUser(@PathVariable("userId") Integer userId) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            userMasterService.activateUser(userId);
            response.setData("User activated successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<VadhyarResponse> deleteUserById(@PathVariable("userId") Integer userId) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            userMasterService.deleteUser(userId);
            response.setData("User deleted successfully...");
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<VadhyarResponse> loginUser(@RequestParam("userName") String userName,
                                                     @RequestParam("password") String password) {
        ResponseEntity<VadhyarResponse> responseEntity;
        VadhyarResponse response = new VadhyarResponse();
        try {
            UserDomain userDomain = userMasterService.login(userName, password);
            response.setData(userDomain);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }


}
