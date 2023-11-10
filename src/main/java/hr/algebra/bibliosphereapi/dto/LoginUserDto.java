package hr.algebra.bibliosphereapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class LoginUserDto {

    @NotNull
    public String username;

    @NotNull
    private String password;



    public LoginUserDto() {
    }

    public LoginUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
