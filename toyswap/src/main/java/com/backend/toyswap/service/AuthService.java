package com.backend.toyswap.service;

import com.auth0.jwt.interfaces.Claim;
import com.backend.toyswap.util.JWTEncoderDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTEncoderDecoder jwtEncoderDecoder;

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger("AuthService");

    public String getLoggedInUserEmail(String authorizationHeader) {
        try {
            // remove "Bearer" prefix from header
            String token = authorizationHeader.split(" ")[1];

            Map<String, Claim> claims = jwtEncoderDecoder.verify(token);
            return claims.get("email").asString();
        } catch (Exception e) {}

        return "";
    }

    public String createAuthToken(String email) {
        HashMap<String, String> claims = new HashMap<>();
        claims.put("email", email);
        return jwtEncoderDecoder.createToken(claims);
    }

    public String authenticate(String email, String password) throws AuthenticationException {
        @SuppressWarnings("unused")
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return createAuthToken(email);
    }
}
