package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final RegisterService registerService;

    @Autowired
    public LoginController(RegisterService registerService){
        this.registerService = registerService;
    }

    /*
    * Verify login
    *
    * @param User
    * @return
    * */
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody User user) {

        // Find User and Verified by mobile and password
        User theUser = registerService.findUser(user.getMobile(), user.getPassword());

        // If user verified, return userId
        if (theUser != null) {
            return ResponseEntity.ok(theUser.getUserId());
        }

        // If user not verified, return null
        return ResponseEntity.ok(null);
    }

}
