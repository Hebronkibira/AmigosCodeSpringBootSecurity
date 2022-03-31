package com.hebronworks.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserDetailsService(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    /**
     * Fetches the UserDetails of a user based on the provided username.
     *
     * @param username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return applicationUserDao.selectUserByUsername(username).//Can be changed with different ways of getting the user
                orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
