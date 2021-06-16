package com.example.springoauth2client.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Getter
@ToString
public class OAuthAttributes {

    private final String id;
    private final Map<String, Object> attributes;

    @Builder
    public OAuthAttributes(String id, Map<String, Object> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public static OAuthAttributes from(OAuth2User oAuth2User) {

        return OAuthAttributes.builder()
                .id(oAuth2User.getName())
                .attributes(oAuth2User.getAttributes())
                .build();
    }
}
