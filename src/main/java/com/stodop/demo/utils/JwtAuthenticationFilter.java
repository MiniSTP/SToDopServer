package com.stodop.demo.utils;

import com.stodop.demo.component.JwtTokenProvider;
import com.stodop.demo.dao.UserDao;
import com.stodop.demo.model.Users;
import com.stodop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = httpServletRequest.getHeader("Authorization");
            String jwtToken = null;
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                jwtToken = bearerToken.substring(7);
            }
            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                String username = jwtTokenProvider.getEmail(jwtToken);
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken
                            authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
