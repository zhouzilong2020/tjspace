package com.tjspace.utils.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * JWT token
 *
 * @author zhouzilong
 */
public class JwtUtils {

    /**
     * 过期时间
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24;
    /**
     * APP密钥
     */
    private static final String APP_SECRET = "ljasdfgipu9g1op=-98cn,m";

    /**
     * 根据id， nickname 生成JWT token
     *
     * @param id       用户id
     * @param nickname 用户昵称
     * @return JWT token
     */


    public static String getJwtToken(String id, String nickname) {
        String token = Jwts.builder()
                // 设置JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 设置过期时间
                .setSubject("TJSPACE-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 设置token主体部分，存储用户信息
                .claim("id", id)
                // 签名
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return token;
    }

    /**
     * 判断token是否存在与有效， 是否是伪造
     *
     * @param jwtToken 用户传入Token
     * @return 是否有效
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            // 解析token是否有效
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取token生成时间
     *
     * @param token 传入request对象，token从header传入
     * @return 生成时间
     */
    public static Date getIssueTimeJwtToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        // 得到token用户主体值
        Claims claims = claimsJws.getBody();
        return claims.getIssuedAt();

    }

    /**
     * 根据token获取用户id
     *
     * @param token 传入request对象，token从header传入
     * @return 用户id
     */
    public static String getUserIdByJwtToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        // 得到token用户主体值
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}
