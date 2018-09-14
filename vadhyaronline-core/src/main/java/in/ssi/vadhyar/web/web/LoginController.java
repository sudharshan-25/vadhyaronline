package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.authentication.VOAuthenticated;
import in.ssi.vadhyar.web.domain.UserDetails;
import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.LoginService;
import in.ssi.vadhyar.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class LoginController {

    private LoginService loginService;

    private UserService userService;

    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public VOResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        VOResponse voResponse = new VOResponse();
        voResponse.setData(loginService.login(userName, password));
        return voResponse;
    }

    @PostMapping("/logout")
    @VOAuthenticated
    public VOResponse logout() {
        VOResponse voResponse = new VOResponse();
        loginService.logOut();
        voResponse.setData("Logged out successfully");
        return voResponse;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<VOResponse> registerUser(@RequestBody UserDetails userDomain) {
        VOResponse response = new VOResponse();
        userService.createUser(userDomain);
        response.setData("User Added successfully");
        return ResponseEntity.ok(response);
    }
}
