package com.example.springoauth2client.config;

import com.example.springoauth2client.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String registrationId = clientRegistration.getRegistrationId();
        String userNameAttributeName = clientRegistration
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        return oAuth2User;
    }

//    private Account saveOrUpdate(OAuthAttributes attributes) {
//        Account account = accountRepository.findByEmail(attributes.getEmail())
//                .map(authAccount -> authAccount.updateProfile(attributes))
//                .orElseGet(() -> Account.from(attributes));
//
//        return accountRepository.save(account);
//    }
}
