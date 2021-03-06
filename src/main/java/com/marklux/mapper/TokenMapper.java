package com.marklux.mapper;

import com.marklux.domain.Token;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mark on 17/11/1.
 */
public interface TokenMapper {
    public Token getToken(@Param("userId") long userId,@Param("client") int client);

    public Token getTokenByStr(@Param("token") String token);

    public int createToken(Token token);

    public int updateToken(Token token);

    public int deleteToken(String token);
}
