package com.mark1708.botapicore.model.security;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiBotKey extends UsernamePasswordAuthenticationToken {
    public ApiBotKey(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public ApiBotKey(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
