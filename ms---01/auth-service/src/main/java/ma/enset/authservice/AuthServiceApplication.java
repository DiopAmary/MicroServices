package ma.enset.authservice;

import ma.enset.authservice.entities.RoleEntity;
import ma.enset.authservice.entities.UserEntity;
import ma.enset.authservice.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    CommandLineRunner start(Services services){
        return args -> {
            services.addRole(new RoleEntity(null, "USER"));
            services.addRole(new RoleEntity(null, "ADMIN"));
            services.addRole(new RoleEntity(null, "CUSTOMER_MANAGER"));
            services.addRole(new RoleEntity(null, "PRODUCT_MANAGER"));
            services.addRole(new RoleEntity(null, "BILLS_MANAGER"));

            services.addUser(new UserEntity(null, "utilisateur-01", bCryptPasswordEncoder.encode("12345"), new ArrayList<>()));
            services.addUser(new UserEntity(null, "utilisateur-02", bCryptPasswordEncoder.encode("12345"), new ArrayList<>()));
            services.addUser(new UserEntity(null, "utilisateur-03", bCryptPasswordEncoder.encode("12345"), new ArrayList<>()));
            services.addUser(new UserEntity(null, "utilisateur-04", bCryptPasswordEncoder.encode("12345"), new ArrayList<>()));
            services.addUser(new UserEntity(null, "administrateur", bCryptPasswordEncoder.encode("12345"), new ArrayList<>()));

            services.addRoleToUser("utilisateur-01", "USER");

            services.addRoleToUser("administrateur", "ADMIN");

            services.addRoleToUser("utilisateur-02", "USER");
            services.addRoleToUser("utilisateur-02", "CUSTOMER_MANAGER");

            services.addRoleToUser("utilisateur-03", "USER");
            services.addRoleToUser("utilisateur-03", "PRODUCT_MANAGER");

            services.addRoleToUser("utilisateur-04", "USER");
            services.addRoleToUser("utilisateur-04", "BILLS_MANAGER");
        };
    }

}
