package com.lin.garbagesorting.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

        @Slf4j
        public class JwtUtils {

            private static long time = 5*1000 ;
            private static String secret = "q1w2^&*&*%";

            public static String createToken(){
                JwtBuilder jwtBuilder = Jwts.builder();
                String jwtToken = jwtBuilder
                        //header
                        .setHeaderParam("typ", "JWT")
                        .setHeaderParam("alg", "HS256")
                        //payload
                        .claim("username", "admin")
                        .claim("role", "admin")
                        .setSubject("admin-test")
                        .setExpiration(new Date(System.currentTimeMillis()+time))
                        .setId(UUID.randomUUID().toString())
                        //signature
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return jwtToken;
            }

            public static boolean checkToken(String token){
                log.info("使用JWTUtil.....");
                if(token == null){
                    log.info("token为空");
                    return false;
                }
                try {
                    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);//解析token信息赋值给claimsJws
                } catch (Exception e) {
                    log.info("解析异常");
                    return false;
                }
                log.info("解析成功,token有效");
                return true;
            }


}
