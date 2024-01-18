package hr.algebra.bibliosphereapi.test.security.jwt;

import hr.algebra.bibliosphereapi.security.jwt.JwtUtils;

public class MockJwtUtils extends JwtUtils {
    @Override
    public boolean validateJwtToken(String authToken) {
        // Always return true during tests
        return true;
    }

    // Add other methods if needed
}

