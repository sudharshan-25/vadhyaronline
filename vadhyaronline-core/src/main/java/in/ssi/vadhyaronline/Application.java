package in.ssi.vadhyaronline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(value = "in.ssi.vadhyaronline")
@EnableJpaRepositories(value = "in.ssi.vadhyaronline.dao")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
