package idv.victoria.socialplatformt.controller;

import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    /*
    * Verify, Register user with Sanitized content
    *
    * @param User
    * @return
    * */
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody User user) {

        // Verify user
        User theUser = registerService.findUser(user.getMobile(), user.getPassword());

        // If user existed, return false to the web page
        if (theUser != null) {
            return ResponseEntity.ok(false);
        }

        // Sanitized biography to prevent XSS attack
        String sanitizedBiography = HtmlUtils.htmlEscape(user.getBiography());

        // Set sanitized biography
        user.setBiography(sanitizedBiography);

        // If user is new, save user
        boolean saved = registerService.saveUser(user);

        return ResponseEntity.ok(saved);

    }


}
