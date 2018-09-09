package in.ssi.vadhyar.web.web;

import in.ssi.vadhyar.web.domain.VOResponse;
import in.ssi.vadhyar.web.service.LoginService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public VOResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        VOResponse voResponse = new VOResponse();
        voResponse.setData(loginService.login(userName, password));
        return voResponse;
    }

}
