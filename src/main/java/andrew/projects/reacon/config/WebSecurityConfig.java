package andrew.projects.reacon.config;

import andrew.projects.reacon.entities.User;
import andrew.projects.reacon.repos.UserRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }
    @Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepo) {
        return map -> {
            String id = (String) map.get("sub");

            User user = userRepo.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setIdUser(id);
                newUser.setFullName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserpic((String) map.get("picture"));

                return newUser;
            });

            user.setLastTimeOnline(LocalDateTime.now());

            return userRepo.save(user);
        };
    }
}
