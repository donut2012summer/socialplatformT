package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User findUser(String mobile, String password) {
        try {
            // Check if user verified
            User user = userRepository.hasUserVerified(mobile, password);

            if (user == null) {
                return null;
            }else {
                return user;
            }
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return user;
//            }

        } catch (Exception e) {
//            logger.error("Error finding user by email: {}", email, e);
            throw new RuntimeException("Error finding user by email: " + mobile, e);
        }
    }
}
