package com.example.springoauth2client.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class KakaoOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {
    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        String providerType = authorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();

        OAuth2User oAuth2User = (OAuth2User) principal.getPrincipal();
        String id = oAuth2User.getName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String nickname = "";
        String email = "";
        String phoneNumber = "";

        if (attributes.containsKey("nickname")) {
            nickname = oAuth2User.getAttribute("nickname");
        }
        if (attributes.containsKey("email")) {
            email = oAuth2User.getAttribute("email");
        }
        if (attributes.containsKey("phone_number")) {
            phoneNumber = oAuth2User.getAttribute("phone_number");
        }

        String refreshTokenValue = "";
        if (refreshToken != null) {
            refreshTokenValue = refreshToken.getTokenValue();
        }

        log.info("OAuth 성공! id: {}, nickname: {}, email: {}, phone_number: {}", id, nickname, email,
                phoneNumber);
        log.info("OAuth 정보! providerType: {}, accessToken: {}, refreshTokenValue: {}", providerType,
                accessToken.getTokenValue(), refreshTokenValue);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }
}
