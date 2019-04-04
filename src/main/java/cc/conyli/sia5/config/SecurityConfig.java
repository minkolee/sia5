package cc.conyli.sia5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存中存储用户数据
        auth
                .inMemoryAuthentication()
                .withUser("jenny")
                .password("{noop}fflym0709")
                .authorities("ROLE_USER")

                .and()

                .withUser("lee0709")
                .password("{noop}jenny0217!@")
                .authorities("ROLE_USER");

    }

}
