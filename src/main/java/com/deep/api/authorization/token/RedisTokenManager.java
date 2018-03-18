package com.deep.api.authorization.token;

import com.deep.api.authorization.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 */
@Component
public class RedisTokenManager implements TokenManager{


    private RedisTemplate<Long, String> redis;

//    @Autowired
//    public void setRedis (RedisTemplate redis) {
//        // 泛型设置成 Long 后必须更改对应的序列化方案
//        this.redis = redis;
//        // RedisTemplate默认采用的是JDK的序列化策略, 保存的key和value都是采用此策略序列化保存的
//        redis.setKeySerializer(new JdkSerializationRedisSerializer());
//    }

    @Override
    public TokenModel createToken(long userId) {
        // 使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        // 存储到Redis并设置过期时间, 分别对应value, 缓存时间, 缓存单位, 如下设置超时时间为3个小时
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            // 验证失败
            return false;
        }
        // 验证成功, 用户进行了一个有效的操作, 延长token的过期时间
        redis.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        // 使用userID和源token简单拼接成的token, 可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(long userId) {
        redis.delete(userId);
    }
}
