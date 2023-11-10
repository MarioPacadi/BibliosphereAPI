package hr.algebra.bibliosphereapi.dto;

import lombok.Getter;

@Getter
public class TokensDto {

    private String accessToken;

    private String refreshToken;

    public TokensDto() {
    }

    public TokensDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
