package com.marklux.services;

import com.marklux.common.Utils;
import com.marklux.domain.Token;
import com.marklux.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark on 17/11/1.
 */
@Service
public class TokenService {
    @Autowired
    private TokenMapper tokenMapper;

    // 一些固定的配置
    public static final int TOKEN_EXPIRE_TIME = 108000000; // 不自动登出
    public static final boolean CHECK_EXPIRE = false;

    public String createToken(long userId, int client, String ip) {
        Token currentToken = tokenMapper.getToken(userId, client);
        String newToken = Utils.createUUID();
        long currentTime = Utils.createTimestamp();

        if (currentToken != null) {
            // 更新
            currentToken.setUpdatedAt(currentTime);
            currentToken.setExpiresAt(currentTime + TOKEN_EXPIRE_TIME);
            currentToken.setToken(newToken);

            tokenMapper.updateToken(currentToken);
        } else {
            Token token = new Token();
            token.setToken(newToken);
            token.setExpiresAt(currentTime + TOKEN_EXPIRE_TIME);
            token.setToken(newToken);
            token.setUserId(userId);
            token.setClient(client);
            token.setCreatedAt(currentTime);
            token.setUpdatedAt(currentTime);

            tokenMapper.createToken(token);
        }

        return newToken;
    }

    public boolean checkToken(String token) {
        Token oldToken = tokenMapper.getToken(token);
        if (oldToken == null) {
            return false;
        }
        if (CHECK_EXPIRE) {
            long currentTime = Utils.createTimestamp();
            if (oldToken.getExpiresAt() < currentTime) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteToken(String token) {
        return tokenMapper.deleteToken(token) == 1;
    }
}
