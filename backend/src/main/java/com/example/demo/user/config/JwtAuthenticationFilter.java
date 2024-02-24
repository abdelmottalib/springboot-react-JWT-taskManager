package com.example.demo.user.config;


import com.example.demo.user.auth.BlackListTokens;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Component
@RequiredArgsConstructor//it will all final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {//the filter will be executed once per request
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final BlackListTokens blackListTokens;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            
//            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);//each filter is responsible to check for one type of authentication
            return;
        }
        try {
            jwt = authHeader.substring(7);
            blackListTokens.printBlackList();
            if (blackListTokens.isTokenInBlackList(jwt)) {
                
                response.setStatus(SC_UNAUTHORIZED);
                response.getWriter().write("Access token blacklisted");
                return;
            }

            userEmail = jwtService.extractUsername(jwt);
            
            
            //check if the user is already authenticated
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    //this object is needed by spring and contextholder to update the security context
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) { // catch the exception if the token has expired
            
            response.setStatus(SC_UNAUTHORIZED);
            response.getWriter().write("Access token expired");
        }
        catch (MalformedJwtException ex) { // catch the exception if the token is malformed
            
            response.setStatus(SC_UNAUTHORIZED);
            response.getWriter().write("JWT is malformed");
        } catch (UnsupportedJwtException exception) { // catch the exception if the token is unsupported
            
            response.setStatus(SC_UNAUTHORIZED);
            response.getWriter().write("JWT is unsupported");
        } catch (Exception ex) { // catch the exception if any other error occurs
            
            response.sendError(SC_INTERNAL_SERVER_ERROR, "Failed to extract username from JWT");
        }

    }
}
