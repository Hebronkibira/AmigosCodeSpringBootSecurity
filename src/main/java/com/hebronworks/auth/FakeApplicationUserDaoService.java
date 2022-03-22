package com.hebronworks.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.hebronworks.securityconfig.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectUserByUsername(String username) {
        return getApplicationUsers().stream().filter(ApplicationUser -> ApplicationUser.getUsername().equals(username)).findFirst();
    }

    private Set<ApplicationUser> getApplicationUsers() {
        Set<ApplicationUser> applicationUsers =Set.of(
                new ApplicationUser(STUDENT.getGrantedAuthorities(),
                        "annasmith",
                        passwordEncoder.encode("password123"),
                        true, true, true, true),
                new ApplicationUser(ADMIN.getGrantedAuthorities(),
                        "moorej",
                        passwordEncoder.encode("password123"),
                        true, true, true, true),
                new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(),
                        "tom",
                        passwordEncoder.encode("password123"),
                        true, true, true, true)
        );
        return applicationUsers;
    }
}
