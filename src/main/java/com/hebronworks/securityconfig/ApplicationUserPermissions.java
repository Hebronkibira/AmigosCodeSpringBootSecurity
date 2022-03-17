package com.hebronworks.securityconfig;

public enum ApplicationUserPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student_write"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
