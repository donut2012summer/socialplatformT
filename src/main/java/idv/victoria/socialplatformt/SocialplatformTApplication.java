package idv.victoria.socialplatformt;

import idv.victoria.socialplatformt.model.Comment;
import idv.victoria.socialplatformt.model.User;
import idv.victoria.socialplatformt.model.dao.UserRepository;
import idv.victoria.socialplatformt.service.CommentService;
import idv.victoria.socialplatformt.service.LoginService;
import idv.victoria.socialplatformt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class SocialplatformTApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialplatformTApplication.class, args

        );
    }

    @Component
    class LoginServiceTester implements CommandLineRunner {

        @Autowired
        private CommentService commentService;

        @Override
        public void run(String... args) throws Exception {
//            List<Comment> commentList = commentService.getCommentsByPostId(4L);
//            System.out.println(commentList.get(0).getContent());
        }
    }
}
