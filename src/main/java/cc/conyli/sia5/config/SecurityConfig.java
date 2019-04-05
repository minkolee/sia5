package cc.conyli.sia5.config;

import cc.conyli.sia5.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService userDetailService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存中存储用户数据
//        auth
//                .inMemoryAuthentication()
//                .withUser("jenny")
//                .password("{noop}fflym0709")
//                .authorities("ROLE_USER")
//
//                .and()
//
//                .withUser("lee0709")
//                .password("{noop}jenny0217!@")
//                .authorities("ROLE_USER");

        //使用自定义的用户数据服务进行验证
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(encoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/ingredient/**", "/taco/**", "/order/**", "/cancel")
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/auth")
                .usernameParameter("user")
                .passwordParameter("pswd")
                .defaultSuccessUrl("/taco/form", true)
                .and()
                .logout()
                    .logoutSuccessUrl("/login");
    }
}
