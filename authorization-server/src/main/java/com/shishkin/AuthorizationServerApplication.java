package com.shishkin;

import com.shishkin.model.User;
import com.shishkin.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.save(new User(
                    1L,
                    "user",
                    passwordEncoder.encode("user"),
                    "user@gmail.com",
                    "ROLE_ADMIN"));
            userRepository.save(new User(
                    2L,
                    "user2",
                    passwordEncoder.encode("user"),
                    "user2@gmail.com",
                    "ROLE_USER"));
        };
    }

}
