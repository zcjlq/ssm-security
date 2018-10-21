package com.ssm.security.app.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/9/17 下午9:10
 */
public class SsmJwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> info = new HashMap<>();
        info.put("company", "zcjlq");

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
