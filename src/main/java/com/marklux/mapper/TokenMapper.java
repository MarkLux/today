package com.marklux.mapper;

import com.marklux.domain.Token;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mark on 17/11/1.
 */
public interface TokenMapper {
    public Token getToken(@Param("user_id") long userId,@Param("client") int client);

    public Token getToken(@Param("token") String token);

    public int createToken(Token token);

    public int updateToken(Token token);

    public int deleteToken(String token);
}
