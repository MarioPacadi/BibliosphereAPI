package hr.algebra.bibliosphereapi.dto;

import hr.algebra.bibliosphereapi.models.Account;
import lombok.Getter;

@Getter
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private int roleId;

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Account getUser(){
        return new Account(this.id,this.username,this.password,this.email,this.roleId);
    }
}
