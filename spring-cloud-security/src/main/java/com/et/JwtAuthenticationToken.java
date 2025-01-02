package com.et;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;

    public JwtAuthenticationToken(UserDetails principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true); // 设置认证成功
    }

    @Override
    public Object getCredentials() {
        return null; // JWT 不需要密码
    }

    @Override
    public Object getPrincipal() {
        return principal; // 返回认证后的用户
    }
}
