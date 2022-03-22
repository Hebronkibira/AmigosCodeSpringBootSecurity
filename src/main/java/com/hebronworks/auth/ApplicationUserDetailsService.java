package com.hebronworks.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private  final  ApplicationUserDao applicationUserDao;

     @Autowired
    public ApplicationUserDetailsService(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    /**
     * This method can be used with different implementation for getting the user from the database
     * @return UserDetails
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return applicationUserDao.selectUserByUsername(username).//Can be changed with different ways of getting the user
                orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found",username)));
    }
}
