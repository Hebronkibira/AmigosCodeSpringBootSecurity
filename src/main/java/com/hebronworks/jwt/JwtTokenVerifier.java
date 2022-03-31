package com.hebronworks.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Get the token from the header (1)
        String authorizationHeader = request.getHeader("Authorization");
        //Check if the authorization Header is present and starts with 'Bearer ' (2)
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Get the actual token from the authorization Header (3)
        String token = authorizationHeader.replace("Bearer", "");
        try {
            String secreKkey = "randomSecureStringThatIhaveJustGeneratedForThePurposeOfthiTutorial";
            //Get the claims from the Jwt tokem using the same key used generate (4)
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secreKkey.getBytes(StandardCharsets.UTF_8)))
                    .parseClaimsJws(token);
            //Extract individual portions of the claim into variables(5)
            Claims body = claimsJws.getBody();
            String subject = body.getSubject();
            //Get the authorities from the body (6).
            var authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(
                    auth -> new SimpleGrantedAuthority(auth.get("authority"))).collect(Collectors.toSet()
            );
            //Make the security context aware that it can authenticate the user (7)

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    subject, null, grantedAuthorities
            );
            // Tell Spring security that this user is authenticated (8)
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s can no be verified ", token));
        }
        filterChain.doFilter(request, response);
    }
}
