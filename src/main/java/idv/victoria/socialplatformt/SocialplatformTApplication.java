package idv.victoria.socialplatformt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SocialplatformTApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialplatformTApplication.class, args
        );
    }

    @Component
    class LoginServiceTester implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            // To do
        }
    }
}
