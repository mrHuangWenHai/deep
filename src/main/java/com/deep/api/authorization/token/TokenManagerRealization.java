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
        if (param.length != 4) {
            return null;
        }
        // 使用userID和源token简单拼接成的token, 可以增加加密措施
        long userId = Long.parseLong(param[0]);
        return new TokenModel(userId, param[1] + ":" + param[2] + ":" + param[3]);
    }

    /**
     * 获取角色的ID
     * @param authentication
     * @return
     */
    public long getRoleID(String authentication) {
        System.out.println(authentication);
        if (authentication == null || authentication.length() == 0) {
            return -1;
        }
        String[] param = authentication.split(":");
        System.out.println(param.length);
        if (param.length != 4) {
            return -1;
        }
        return Long.parseLong(param[2]);
    }

    @Override
    public void deleteToken(long userId) {

    }
}
