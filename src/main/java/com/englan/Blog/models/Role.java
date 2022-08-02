package com.englan.Blog.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;
    @Override
    public String getAuthority() {
        return name();
    }
}