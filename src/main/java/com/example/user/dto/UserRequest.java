package com.example.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest implements Serializable {
    @NotNull (message = "Nombre debe de ser informado")
    private String name;

    @NotNull (message = "Email debe de ser informado")
    @Email
    private String email;

    //@Size(min = 4, message="Password debe de tener minimo 4 caracteres")
    @NotNull (message = "Password debe de ser informado")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,15}$")
    private String password;

    private List<UserPhone> phones;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserPhone> getPhones() {
        return phones;
    }

    public void setUserPhones(List<UserPhone> userPhones) {
        this.phones = phones;
    }
}
