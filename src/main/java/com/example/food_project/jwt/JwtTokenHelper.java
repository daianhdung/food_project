package com.example.food_project.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenHelper {
    private final long expiredDate = 8 * 60 * 60 * 1000;
    private final String strKey = "YWRtaW4xMjM0NTY3ODkwYWRtaW4xMjM0NTY3ODkwYWRtaW4xMjM0NTY3ODkwCg=="; //Chuỗi BASE64

    public String generateToken(String data){

        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + expiredDate);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        return Jwts.builder()
                .setSubject(data) // Lữu trữ dữ liệu vào trong token type String
                .setIssuedAt(now)//Thời gian tạo ra token
                .setExpiration(dateExpired)//Thời gian hết hạn của token
                .signWith(secretKey, SignatureAlgorithm.HS256)//Thuật toán mã hóa và secret key
                .compact();
    }

    public String decodeToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(strKey));
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
