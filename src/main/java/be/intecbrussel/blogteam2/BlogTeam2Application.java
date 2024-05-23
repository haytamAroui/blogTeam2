package be.intecbrussel.blogteam2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogTeam2Application {

    public static void main(String[] args) {
        SpringApplication.run(BlogTeam2Application.class, args);
    }

}
