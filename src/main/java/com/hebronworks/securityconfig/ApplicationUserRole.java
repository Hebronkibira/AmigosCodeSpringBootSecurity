package com.hebronworks.securityconfig;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hebronworks.securityconfig.ApplicationUserPermissions.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_WRITE, STUDENT_READ, COURSE_READ, COURSE_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRole(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;

    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set <SimpleGrantedAuthority> permisions=getPermissions().stream().map(permision->
                new SimpleGrantedAuthority(permision.getPermission())).collect(Collectors.toSet()
        );
        permisions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
      return permisions;
    }
}
