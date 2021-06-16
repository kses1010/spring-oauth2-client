package com.example.springoauth2client.oauth;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Getter
public class KakaoOAuth2User implements OAuth2User {

    private final String id;
    private final Set<SimpleGrantedAuthority> authorities;
    private final Map<String, Object> kakaoAccount;
    private final Map<String, Object> properties;

    @Builder
    public KakaoOAuth2User(String id, Set<SimpleGrantedAuthority> authorities,
                           Map<String, Object> kakaoAccount, Map<String, Object> properties) {
        this.id = id;
        this.authorities = authorities;
        this.kakaoAccount = kakaoAccount;
        this.properties = properties;
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.putAll(kakaoAccount);
        attributes.putAll(properties);
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return this.id;
    }

    public static KakaoOAuth2User from(OAuthAttributes oAuthAttributes) {
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        Map<String, Object> attributes = oAuthAttributes.getAttributes();
        return KakaoOAuth2User.builder()
                .id(oAuthAttributes.getId())
                .kakaoAccount((Map<String, Object>) attributes.get("kakao_account"))
                .kakaoAccount((Map<String, Object>) attributes.get("properteis"))
                .authorities(authorities)
                .build();
    }
}
