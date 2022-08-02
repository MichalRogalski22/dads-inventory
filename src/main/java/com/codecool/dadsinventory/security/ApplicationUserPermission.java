package com.codecool.dadsinventory.security;

public enum ApplicationUserPermission {
    FULL_ACCESS("full_access"),
    RESTRICTED_ACCESS("restricted_access");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
