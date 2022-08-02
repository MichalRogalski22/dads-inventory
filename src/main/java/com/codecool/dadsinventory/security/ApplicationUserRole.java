package com.codecool.dadsinventory.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    MOM(Sets.newHashSet(ApplicationUserPermission.RESTRICTED_ACCESS)),
    DAD(Sets.newHashSet(ApplicationUserPermission.FULL_ACCESS));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
