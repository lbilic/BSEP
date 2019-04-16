package com.kits.project.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.kits.project.repositories.SystemUserRepository;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JWTUtils jwtUtils;
    
    @Autowired
    private SystemUserRepository userRep;

    private HashMap<String, RouteAccess> myMap = createMap();

    private static HashMap<String, RouteAccess> createMap() {
        HashMap<String, RouteAccess> myMap = new HashMap<String,RouteAccess>();
        myMap.put("generateCertificate", new RouteAccess("/api/cert", "POST"));
        myMap.put("getCertificate",  new RouteAccess("/api/cert", "GET"));
        myMap.put("revoke", new RouteAccess("/api/cert", "DELETE"));
        myMap.put("getAllData", new RouteAccess("/api/cert/all-data", "GET"));
        myMap.put("addUser", new RouteAccess("/api/systemUser/user", "PUT"));
        myMap.put("removeUser",new RouteAccess("/api/systemUser/user", "DELETE"));
        myMap.put("editUser", new RouteAccess("/api/systemUser/user", "POST"));
        myMap.put("addRole", new RouteAccess("/api/systemUser/role", "PUT"));
        myMap.put("removeRole", new RouteAccess("/api/systemUser/role", "DELETE"));
        myMap.put("editRole", new RouteAccess("/api/systemUser/role", "POST"));
        myMap.put("addPermission", new RouteAccess("/api/systemUser/permissions", "PUT"));
        myMap.put("editPermission", new RouteAccess("/api/systemUser/permissions", "DELETE"));
        return myMap;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader("Authentication-Token");
        String username = jwtUtils.getUsernameFromToken(authToken);

        if (username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(username);
            if (jwtUtils.validateToken(authToken, userDetails) && this.canUserVisitRoute(((HttpServletRequest) request).getRequestURI(),
                    ((HttpServletRequest) request).getMethod(),
                    userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        chain.doFilter(request, response);
    }

    private boolean canUserVisitRoute(String url, String method, UserDetails userDetails) {    	
        String auth_url= "";
        String auth_method= "";
        for (GrantedAuthority authority: userDetails.getAuthorities()) {
        	auth_url = authority.getAuthority().split("\\|")[0];
        	auth_method = authority.getAuthority().split("\\|")[1];

            if(url.contains(auth_url) && auth_method.equals(method)){
            	return true;
            }
        }
        return false;
    }

}
