package com.hebronworks.auth;
/**
 * This interface can be used by different implementation to get the UserDetails
 * @Author George Kibira
 *
 * */
import java.util.Optional;

public interface ApplicationUserDao {
    /**
     * implementation of this method returns an Optional of UserDetails
     * @param username
     * @return Optional<ApplicationUser>
     **/
    Optional<ApplicationUser> selectUserByUsername(String username);
}
