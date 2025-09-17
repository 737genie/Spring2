package com.example.demo;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private LocalDate expirationDate; // 계정 만료 날짜
    private boolean accountNonLocked; // 계정 잠금 여부
    private boolean credentialsNonExpired; // 비밀번호 만료 여부
    private boolean enabled; // 계정 활성화 여부
    private Collection<? extends GrantedAuthority> authorities;

    // 생성자
    public CustomUserDetails(String username, String password, LocalDate expirationDate,
                             boolean accountNonLocked, boolean credentialsNonExpired,
                             boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.expirationDate = expirationDate;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
}