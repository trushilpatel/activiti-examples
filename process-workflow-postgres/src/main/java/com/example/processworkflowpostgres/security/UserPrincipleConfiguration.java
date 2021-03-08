package com.example.processworkflowpostgres.security;

import com.example.processworkflowpostgres.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipleConfiguration implements UserDetails {
    private final User user;

    UserPrincipleConfiguration(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authoritiesString = new ArrayList<String>();

        if (user.getRoles() == null) {
            authoritiesString.add("ROLE_ACTIVITI_USER");
            authoritiesString.add("GROUP_ACTIVITI_TEAM");
        } else {
            authoritiesString.addAll(Arrays.asList(user.getRoles()));
            authoritiesString.addAll(Arrays.asList(user.getGroups()));
        }

        return authoritiesString.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
