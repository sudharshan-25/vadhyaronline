package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.constants.CommonConstants;
import in.ssi.vadhyaronline.domain.UserDomain;
import in.ssi.vadhyaronline.domain.VadhyarResponse;
import in.ssi.vadhyaronline.service.UserMasterService;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UnAuthUserController {

    private UserMasterService userMasterService;

    private CacheManager cacheManager;

    public UnAuthUserController(UserMasterService userMasterService, CacheManager cacheManager) {
        this.userMasterService = userMasterService;
        this.cacheManager = cacheManager;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<VadhyarResponse> registerUser(@RequestBody UserDomain userDomain) {
        VadhyarResponse response = new VadhyarResponse();
        userMasterService.addUser(userDomain);
        response.setData("User Added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<VadhyarResponse> loginUser(@RequestParam("userName") String userName,
                                                     @RequestParam("password") String password) throws Exception {
        VadhyarResponse response = new VadhyarResponse();
        UserDomain userDomain = userMasterService.login(userName, password);
        cacheManager.getCache(CommonConstants.CacheConstants.LOGIN_USERS).put(userDomain.getToken(), userDomain);
        response.setData(userDomain);
        return ResponseEntity.ok(response);
    }

}