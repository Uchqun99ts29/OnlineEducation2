package com;

import com.education.entity.AuthUser;
import com.education.enums.Role;
import com.education.properties.OpenApiProperties;
import com.education.properties.ServerProperties;
import com.education.repository.AuthUserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})
@OpenAPIDefinition
@RequiredArgsConstructor
public class EducationApplication {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class, args);
    }

    //    @Bean
    CommandLineRunner runner() {
        return (args) -> {
            authUserRepository.deleteAll();
            String encode = passwordEncoder.encode("123");
            System.out.println("encode = " + encode);

            AuthUser admin = AuthUser.childBuilder()
                    .username("admin")
                    .password(encode)
                    .role(Role.ADMIN)
                    .fullName("User")
                    .phone("+998906543210")
                    .build();
            authUserRepository.save(admin);
        };
    }
}
