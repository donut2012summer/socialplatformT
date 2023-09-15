package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody User user) {

        // Check if user existed or not
        User theUser = registerService.findUser(user.getEmail());

        // If user existed, return false to the web page
        if (theUser != null) {
            return ResponseEntity.ok(false);
        }

        // If user is new, save user
        boolean saved = registerService.saveUser(user);
        return ResponseEntity.ok(saved);

    }


}
