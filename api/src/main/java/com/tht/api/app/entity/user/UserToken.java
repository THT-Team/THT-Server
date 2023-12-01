package com.tht.api.app.entity.user;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.exception.custom.UserTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserToken {

    //    private static final long REFRESH_TOKEN_VALID_PERIOD =  1000L * 60 * 24 * 3;
    private static final long REFRESH_TOKEN_VALID_PERIOD = 1000L * 90;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @LastModifiedDate
    @Column
    private LocalDateTime lastModifiedAt;

    private UserToken(String userUuid, String accessToken) {
        this.userUuid = userUuid;
        this.accessToken = accessToken;
        this.refreshToken = generateRefreshToken();
    }

    private String generateRefreshToken() {

        final Date now = new Date();
        final Date refreshTokenExpireIn = new Date(now.getTime() + REFRESH_TOKEN_VALID_PERIOD);

        return Jwts.builder()
            .setSubject("authorization")
            .claim("userUuid", userUuid)
            .setExpiration(refreshTokenExpireIn)
            .compact();
    }

    public static UserToken create(final String userUuid, final String accessToken) {
        return new UserToken(userUuid, accessToken);
    }

    public void checkRefreshExpired() {

        try {
            Jwts.parserBuilder().build().parseClaimsJwt(refreshToken);
        } catch (ExpiredJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 만료된 refresh 토큰입니다.", e.getClass().getName()));
            throw UserTokenException.refreshExpired();
        }
    }

    public void refresh(final String accessToken) {
        this.accessToken = accessToken;
        this.refreshToken = generateRefreshToken();
    }
}

