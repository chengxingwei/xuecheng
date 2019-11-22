package com.xuecheng.member.config;


import com.xuecheng.utils.Result;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SysTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Result result = new Result();
        result.setCode(0);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> userMap = new HashMap<>();
        if (userDetails != null){
            userMap.put("username",userDetails.getUsername());
            if (userDetails.getRoles() != null && userDetails.getRoles().size() > 0){
                userMap.put("roles",userDetails.getRoles());
            }else{
                userMap.put("roles",new ArrayList<>());
            }
            userMap.put("mobile",userDetails.getMobile());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userMap);
        return accessToken;
    }
}
