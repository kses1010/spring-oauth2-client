package com.example.springoauth2client.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class KakaoOAuth2User implements OAuth2User {

    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    private String id;

    @JsonProperty("kakao_account")
    private Map<String, Object> kakaoAccount = new HashMap<>();

    private Map<String, Object> properties = new HashMap<>();

    @Builder
    public KakaoOAuth2User(List<GrantedAuthority> authorities, String id,
                           Map<String, Object> kakaoAccount, Map<String, Object> properties) {
        this.authorities = authorities;
        this.id = id;
        this.kakaoAccount = kakaoAccount;
        this.properties = properties;
    }

    public static KakaoOAuth2User of() {

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
}
