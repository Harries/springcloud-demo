package com.et;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users = new ArrayList<>();

    public InMemoryUserDetailsService() {
        // 模拟用户信息
        users.add(org.springframework.security.core.userdetails.User.withUsername("user")
                .password("{noop}password") // {noop}表示不进行密码加密
                .roles("USER")
                .build());
        users.add(org.springframework.security.core.userdetails.User.withUsername("admin")
                .password("{noop}admin") // {noop}表示不进行密码加密
                .roles("ADMIN")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}