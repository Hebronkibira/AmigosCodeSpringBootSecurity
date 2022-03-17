package com.hebronworks;


import com.hebronworks.securityconfig.ApplicationUserPermissions;
import com.hebronworks.securityconfig.ApplicationUserRole;
import com.hebronworks.securityconfig.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.hebronworks.securityconfig.ApplicationUserPermissions.STUDENT_WRITE;
import static com.hebronworks.securityconfig.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConFig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConFig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAnyAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAnyAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAnyAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmaithUser = User.builder().
                username("annasmith")
                 .password(passwordEncoder.encode("password"))
                .authorities(STUDENT.getGrantedAuthorities())
               // .roles(STUDENT.name())
                .build();

        UserDetails mooreJohnsonUser = User.builder().
                username("moorej")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMIN.getGrantedAuthorities())
                //.roles(ADMIN.name())
                .build();

        UserDetails tomUser = User.builder().
                username("tom")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                //.roles(ADMINTRAINEE.name())
                .build();
        return new InMemoryUserDetailsManager(
                annaSmaithUser,mooreJohnsonUser,tomUser
        );

    }
}
