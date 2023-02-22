package pidev.elbey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.ElBeyService;

import java.util.ArrayList;


@SpringBootApplication
public class PidevApplication {

    public static void main(String[] args) {
        SpringApplication.run(PidevApplication.class, args);
    }


@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}
}
