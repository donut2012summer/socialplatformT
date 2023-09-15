package idv.victoria.socialplatformt.model.dao;

import idv.victoria.socialplatformt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Check if email existed
    @Procedure(name = "HasMobileExists")
    Integer hasMobileExists(@Param("p_mobile") String mobile);


    // Save or Update if user_id is null
    @Procedure(name = "SaveOrUpdateUser")
    void saveOrUpdateUser(@Param("p_user_id") Long userId,
                          @Param("p_user_name") String userName,
                          @Param("p_mobile") String mobile,
                          @Param("p_email") String email,
                          @Param("p_password") String password);



    // Check if email existed
    @Procedure(name = "FindUserByMobile")
    User findUserByMobile(@Param("p_mobile") String mobile);




}
