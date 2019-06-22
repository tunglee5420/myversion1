package com.just.myproject.Utils;

import com.just.myproject.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类
 */
public class JwtUtils {
    public static final String SUBJECT="justGeeker";
    public static final long Ex_Time=60*1000*60*24;//过期时间，毫秒
    public static final String APPSECRET="5420";//密钥

    /**
     * 加密token
     * @param user
     * @return
     */
    public static String creatJwt(User user){

        if(user==null||user.getName()==null||user.getWorknum()==null&&(Integer)user. getIsAdmin()==null&&user.getNickname()==null&&user.getHeadimg()==null){
            return null;
        }
        String token= Jwts.builder().setSubject(SUBJECT)
                .claim("isAdmin",user.getIsAdmin())
                .claim("worknum",user.getWorknum())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+Ex_Time))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();
        return token;
    }

    /**
     * 解密token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try {
            final Claims claims= Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }

    }
}
