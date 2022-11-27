package com.tjspace.gateway.filter;

import com.google.gson.JsonObject;
import com.tjspace.utils.commonutils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局Filter，统一处理用户登录与外部不允许访问的服务
 *
 * @author zhouzilong
 * @since 2020-12-03
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();

        if (antPathMatcher.match("/**/login/**", path)) {
            // 登录URL
            return chain.filter(exchange);
        }
        // 校验用户必须登录
        if (antPathMatcher.match("/**", path)) {
            List<String> tokenList = request.getHeaders().get("token");
            if (null == tokenList) {
                // 用户未传入token
                return globalAuth(response);
            } else if (JwtUtils.checkToken(tokenList.get(0))) {
                String userId = JwtUtils.getUserIdByJwtToken(tokenList.get(0));

                String tokenDB = redisTemplate.opsForValue().get(userId);
                if (tokenDB == null) {
                    // token 过期了
                    return elapsed(response);
                } else if (!tokenDB.equals(tokenList.get(0))) {
                    // 比较用户请求token与数据库中token的时间
                    Date timeDB = JwtUtils.getIssueTimeJwtToken(tokenDB);
                    Date time = JwtUtils.getIssueTimeJwtToken(tokenList.get(0));
                    try {
                        if (timeDB.after(time)) {
                            // 用户token早于数据库token，用户多地方登录，本地下线
                            return singleLogin(response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return globalAuth(response);
                    }
                }
                // 到这里说明 用户单地登录，token没有过期且为最新的一份
                // 为之后的请求设置userId
                request.mutate().header("userId", userId);
                return chain.filter(exchange);
            }
        }
        // 内部服务接口，不允许外部访问
        if (antPathMatcher.match("/**/inner/**", path)) {
            return globalAuth(response);
        }
        return this.globalAuth(response);
    }

    @Override
    public int getOrder() {
        return 0;
    }


    private Mono<Void> elapsed(ServerHttpResponse response) {
        Map<String, Object> params = new HashMap<>();
        params.put("success", false);
        params.put("code", 28004);
        params.put("data", "您过久没有操作，本地已下线");
        return out(response, params);
    }

    private Mono<Void> globalAuth(ServerHttpResponse response) {
        Map<String, Object> params = new HashMap<>();
        params.put("success", false);
        params.put("code", 28004);
        params.put("data", "鉴权失败");
        return out(response, params);
    }

    private Mono<Void> singleLogin(ServerHttpResponse response) {
        Map<String, Object> params = new HashMap<>();
        params.put("success", false);
        params.put("code", 28004);
        params.put("data", "您已经在其他地方登录，本地将下线");
        return out(response, params);
    }


    private Mono<Void> out(ServerHttpResponse response, Map<String, Object> params) {
        JsonObject message = new JsonObject();
        message.addProperty("success", (Boolean) params.get("success"));
        message.addProperty("code", 28004);
        message.addProperty("data", (String) params.get("data"));
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        // 指定编码--中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
