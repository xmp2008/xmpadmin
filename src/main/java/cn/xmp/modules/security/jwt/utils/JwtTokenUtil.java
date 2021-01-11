package cn.xmp.modules.security.jwt.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.xmp.modules.security.jwt.constant.TokenType;
import cn.xmp.modules.security.jwt.domain.UserDetails;
import cn.xmp.modules.security.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 基于JWT的Token工具类
 * </p>
 * @author wangliangx
 * @since 2017/12/19
 */
@Slf4j
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_DEVICE_ID = "deviceId";
    private static final String CLAIM_KEY_DEVICE_TYPE = "deviceType";
    private static final String CLAIM_KEY_LOGIN_USERNAME = "loginUsername";
    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String SHARE_DEFAULT_USER_NAME = "xmpadmin";

    private JwtProperties jwtProperties;

//    @Resource(name = "hazelcastInstance")
//    private HazelcastInstance hazelcastInstance;

    @Autowired
    public JwtTokenUtil(JwtProperties jwtProperties
//            , HazelcastInstance hazelcastInstance
    ) {
        this.jwtProperties = jwtProperties;
//        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * 获取jwt配置信息
     * @return
     */
    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    /**
     * 生成Token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails, String tokenType, Long expriredMillis) {
        Date expirationDate;
        if (expriredMillis == null) {
            if (TokenType.REFRESH_TOKEN.equals(tokenType)) {
                expirationDate = generateExpirationDate(jwtProperties.getRefreshTokenExpiration());
            } else {
                expirationDate = generateExpirationDate(jwtProperties.getAccessTokenExpiration());
            }
        } else {
            expirationDate = new Date(System.currentTimeMillis() + expriredMillis);
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getAccount());
        claims.put(CLAIM_KEY_LOGIN_USERNAME, userDetails.getLoginUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_DEVICE_ID, userDetails.getDeviceId());
        claims.put(CLAIM_KEY_DEVICE_TYPE, userDetails.getDeviceType());
        claims.put(CLAIM_KEY_USER_ID, userDetails.getUserId());
        return generateToken(claims, expirationDate);
    }

    /**
     * 生成Token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails, String tokenType) {
        return generateToken(userDetails, tokenType,null);
    }

    String generateToken(Map<String, Object> claims, Date expirationDate) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtProperties.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//        return Jwts.builder()
//                .setClaims(claims)//数据声明
//                .setExpiration(expirationDate)//过期时间
//                .signWith(SignatureAlgorithm.HS512, DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))//签名（包含算法和密钥）
//                .compact();
        return Jwts.builder().setHeaderParam("typ", "JWT")
                .setClaims(claims)//数据声明
                .setExpiration(expirationDate)//过期时间
                .signWith(signatureAlgorithm, signingKey)//签名（包含算法和密钥）
                .compact();
    }

    private Date generateExpirationDate(Long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    public String refreshToken(String oldToken, Boolean isAddTokenToBlacklist) {
        String refreshedToken;
        try {
            if (isAddTokenToBlacklist) {
//                tokenAddToBlacklist(oldToken);
            }
            final Claims claims = getClaimsFromToken(oldToken);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims, generateExpirationDate(jwtProperties.getRefreshTokenExpiration()));
        } catch (Exception e) {
            Map<String, Object> claims = new HashMap<>();
            claims.put(CLAIM_KEY_USERNAME, SHARE_DEFAULT_USER_NAME);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims, generateExpirationDate(jwtProperties.getRefreshTokenExpiration()));
        }
        return refreshedToken;
    }

    /**
     * 验证Token
     * @param token
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        Boolean result;
        try {
            final String username = getUsernameFromToken(token);

            //数据声明为空只校验token是否过期
            if (null == userDetails) {
                result = !isTokenExpired(token) ;
//                        && !tokenIsOnTheBlacklist(token);
            } else {
//                final Date created = getCreatedDateFromToken(token);
//                final Date expiration = getExpirationDateFromToken(token);
                result = (userDetails.getAccount().equals(username)//匹配帐号
                        && !isTokenExpired(token)//是否过期
//                        && !tokenIsOnTheBlacklist(token)//是否在黑名单中
//                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
                );
            }
        } catch (Exception e) {
            //don't trust the JWT!
            result = false;
        }
        return result;
    }

    /**
     * 失效Token
     * @param token
     * @return
     */
    public Boolean invalidToken(String token) {
        Boolean result;
        try {
//            result = tokenAddToBlacklist(token);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

//    /**
//     * Token放入黑名单
//     * @param token
//     * @return
//     */
//    Boolean tokenAddToBlacklist(String token) {
//        Boolean result;
//        try {
//            if (!isTokenExpired(token)) {
//                Set<String> tokenSet = HCacheMapUtil.get(hazelcastInstance, Namespace.BLACKLIST, Namespace.BLACKLIST, Set.class);
//                if (CollectionUtils.isEmpty(tokenSet)) {
//                    tokenSet = new HashSet<>();
//                }
//                tokenSet.add(token);
//                HCacheMapUtil.put(hazelcastInstance, Namespace.BLACKLIST, Namespace.BLACKLIST, tokenSet, jwtProperties.getAccessTokenExpiration());
//            }
//            result = true;
//        } catch (Exception e) {
//            result = false;
//        }
//        return result;
//    }

//    /**
//     * 判断Token是否在黑名单中
//     * @param token
//     * @return
//     */
//    Boolean tokenIsOnTheBlacklist(String token) {
//        Boolean result = false;
//        try {
//            Set<String> tokenSet = HCacheMapUtil.get(hazelcastInstance, Namespace.BLACKLIST, Namespace.BLACKLIST, Set.class);
//            if (CollectionUtils.isNotEmpty(tokenSet)) {
//                result = tokenSet.contains(token);
//            }
//        } catch (Exception e) {
//            result = false;
//        }
//        if (result) {
//            log.warn("token is in blackList: {} userDetail: {}", token, getUserDetailsFromToken(token));
//        }
//        return result;
//    }

    /**
     * 获取Token中帐号信息
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取Token中的登录时输入的帐号信息
     * @param token
     * @return
     */
    public String getLoginUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_LOGIN_USERNAME);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取Token中设备唯一标识
     * @param token
     * @return
     */
    public String getDeviceIdFromToken(String token) {
        String deviceId;
        try {
            final Claims claims = getClaimsFromToken(token);
            deviceId = (String) claims.get(CLAIM_KEY_DEVICE_ID);
        } catch (Exception e) {
            deviceId = null;
        }
        return deviceId;
    }

    /**
     * 获取Token中设备类型
     * @param token
     * @return
     */
    public String getDeviceTypeFromToken(String token) {
        String deviceType;
        try {
            final Claims claims = getClaimsFromToken(token);
            deviceType = (String) claims.get(CLAIM_KEY_DEVICE_TYPE);
        } catch (Exception e) {
            deviceType = null;
        }
        return deviceType;
    }

    /**
     * 获取Token创建时间
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获取Token过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 获取Token中的userId信息
     * @param token
     * @return
     */
    public Long getUserIdFromToken(String token) {
        Long userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = (Long) claims.get(CLAIM_KEY_USER_ID);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    /**
     * 获取Token中的 userDetails 信息
     * @param token
     * @return
     */
    public UserDetails getUserDetailsFromToken(String token) {
        UserDetails userDetails;
        try {
            final Claims claims = getClaimsFromToken(token);
            String username = claims.getSubject();
            String loginUsername = (String) claims.get(CLAIM_KEY_LOGIN_USERNAME);
            String deviceId = (String) claims.get(CLAIM_KEY_DEVICE_ID);
            String deviceType = (String) claims.get(CLAIM_KEY_DEVICE_TYPE);
            Long userId = (Long) claims.get(CLAIM_KEY_USER_ID);

            userDetails = new UserDetails();
            userDetails.setAccount(username);
            userDetails.setLoginUsername(loginUsername);
            userDetails.setDeviceId(deviceId);
            userDetails.setDeviceType(deviceType);
            userDetails.setUserId(userId);
        } catch (Exception e) {
            userDetails = null;
        }
        return userDetails;
    }


    /**
     * 获取Token数据声明
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 判断Token是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        boolean before = expiration.before(new Date());
        if (before) {
            log.warn("token is expired, expireDate: {}, token: {}", DateUtil.format(expiration, DatePattern.NORM_DATETIME_PATTERN), expiration);
        }
        return before;
    }

    /**
     * 是否在最后一次密码重置之前创建Token
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 能否刷新Token
     * @param token
     * @param lastPasswordReset
     * @return
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

}
