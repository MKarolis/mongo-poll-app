package com.karolismed.mongo.polling.config.security;
import java.util.Collection;
import java.util.List;

import com.karolismed.mongo.polling.auth.model.User;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private ObjectId id;

    private String userName;

    private String displayName;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(ObjectId id, String userName, String displayName, String password) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.password = password;
        authorities = List.of(
            new SimpleGrantedAuthority("MANAGE_POLLING")
        );
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getDisplayName(),
            user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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