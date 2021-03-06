package com.xuecheng.member.controller;

import com.xuecheng.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@Api("测试")
public class OauthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;



    @GetMapping("/token")
    public Map<String,Object> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
//        return tokenEndpoint.getAccessToken(principal, parameters);
    }

    @ApiOperation(value = "计算+", notes = "加法")
    @PostMapping("/token")
    public Map<String,Object> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    //自定义返回格式
    private Map<String,Object> custom(OAuth2AccessToken accessToken) {
        Map<String,Object> result = new HashMap<>();
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        result.put("access_token",token.getValue());
        result.put("token_type",token.getRefreshToken().getValue());
        result.put("token_type",token.getTokenType());
        result.put("expires_in",token.getExpiresIn());
        result.put("scope",token.getScope());
        Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        result.put("code",0);
        result.put("data",data);
        return result;
    }
}
