package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    // Encoder
    private final PasswordEncoder passwordEncoder;

    // Logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /*
    * Save user
    *
    * @param User
    * @return
    * */
    @Transactional
    public Boolean saveUser(User user) {
        try {

            // Verify user
            String mobile = user.getMobile();

            // If mobile did not exist, return false
            if (userRepository.hasMobileExists(mobile) != 0) {
                return false;
            }

            // Encode password
            String encodedPassword = passwordEncoder.encode(user.getPassword());

            // Set the encoded password
            user.setPassword(encodedPassword);

            // Save user
            userRepository.saveOrUpdateUser(
                    user.getUserId(),
                    user.getUserName(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getBiography()
            );

            return true;

        } catch (Exception e) {
            // Handle Exception
            logger.error("Error saving user with mobile: {}", user.getMobile(), e);
            throw new RuntimeException("Error saving user with mobile: " + user.getMobile(), e);
        }
    }


    /*
    * Verify user by mobile and password
    *
    * @param mobile, password
    * @return
    * */

    @Transactional
    public User findUser(String mobile, String password) {
        try {

            // Verify mobile number
            User user = userRepository.findUserByMobile(mobile);
            if (user == null) {
                return null;
            }

            // Verified password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }

            return null;

        } catch (Exception e) {
            // Handle Exception
            logger.error("Error finding user by mobile: {}", mobile, e);
            throw new RuntimeException("Error finding user by mobile: " + mobile, e);
        }
    }
}
