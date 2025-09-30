package com.morrisco.net.store.onlineStoreSystem.filter;

import com.morrisco.net.store.onlineStoreSystem.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService service;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader=request.getHeader("Authorization");

        if (authHeader ==null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader.replace("Bearer ","");
        if(!service.validateToken(token)){
            filterChain.doFilter(request,response);
            return;
        }
        /**
         *  new UsernamePasswordAuthenticationToken() -> its contructor is used for creating
         *  1.Anonymous User
         *  2.Authentic User
         */
        var authentication = new UsernamePasswordAuthenticationToken(service.getUserIdFromToken(token),null,null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//attaching metadata to authentication object like IP address


        SecurityContextHolder.getContext().setAuthentication(authentication);//storing information about the authenticated User

        filterChain.doFilter(request,response);//passing request to the next filter in the chain

    }
}
