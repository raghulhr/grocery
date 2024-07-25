package com.grocery.apigateway.filter;

import com.grocery.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);

                    // Extract username and roles
                    String username = jwtUtil.extractUsername(authHeader);
                    List<String> roles = jwtUtil.extractRoles(authHeader);

                    request = exchange.getRequest()
                            .mutate()
                            .header("loggedInUser", username)
                            .build();

                    // Check if the user has the required role
                    if (!hasRequiredRole(roles, request)) {
                        throw new RuntimeException("Unauthorized access to this resource");
                    }
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            assert request != null;
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    private boolean hasRequiredRole(List<String> roles, ServerHttpRequest request) {
        // Define the roles required for specific endpoints
        // This can be replaced with a more dynamic role-checking mechanism as needed
        if (request.getURI().getPath().startsWith("/owner")) {
            return roles.contains("ROLE_OWNER");
        } else if (request.getURI().getPath().startsWith("/user")) {
            return roles.contains("ROLE_USER");
        } else {
            // Default to allowing access for other roles/endpoints
            return true;
        }
    }

    public static class Config {

    }
}
