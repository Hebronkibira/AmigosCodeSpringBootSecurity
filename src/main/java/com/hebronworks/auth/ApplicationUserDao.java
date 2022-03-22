package com.hebronworks.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectUserByUsername(String username);
}
