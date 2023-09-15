package idv.victoria.socialplatformt.service;

import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public Boolean saveUser(User user) {
        try {

            // Check if Email exists
            String mobile = user.getMobile();

            // If Email not existed, return false
            if (userRepository.hasMobileExists(mobile) != 0) {
                return false;
            }

            // If Email not existed, save User
            userRepository.saveOrUpdateUser(
                    user.getUserId(),
                    user.getUserName(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getPassword()
            );
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            // TO-DO try to handle by logger
            throw new RuntimeException("Error saving user with email: " + user.getEmail(), e);
        }
    }


    @Transactional
    public User findUser(String mobile) {
        try {
            // Check if user existed by mobile
            User user = userRepository.findUserByMobile(mobile);

            // If user did not exist
            if (user == null) {
                return null;
            }

            // If user existed
            // TO-DO need to write constraints for returning user
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            // try to handle by logger
            throw new RuntimeException("Error finding user by email: " + mobile, e);
        }
    }
}
