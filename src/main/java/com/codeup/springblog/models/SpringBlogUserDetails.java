package com.codeup.springblog.models;


//granted authorities is null
//other account @Overrides are all return true


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SpringBlogUserDetails extends User implements UserDetails {
    public SpringBlogUserDetails(long id, String username, String email, String password) {
        super (id, username, email, password);
    }

    public SpringBlogUserDetails(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
