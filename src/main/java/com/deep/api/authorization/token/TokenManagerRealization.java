package com.deep.api.authorization.token;

import org.springframework.stereotype.Service;

@Service
public class TokenManagerRealization implements TokenManager{
    @Override
    public TokenModel createToken(long userId) {
        return null;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        return false;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split(":");
        if (param.length != 2) {
            return null;
        }
        // 使用userID和源token简单拼接成的token, 可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        System.out.println(token);
        return new TokenModel(new Long(userId), token);
    }

    @Override
    public void deleteToken(long userId) {

    }
}
