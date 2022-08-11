package com.example.MSIAM.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Table(name = "user_details")
@Entity
@Data
public class User {

    @Override
    public String toString() {
        return "User{" +
                "id=" + uuid +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", authorities='" + authority + '\'' +
                '}';
    }

    @Id
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String email;

    private String authority;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authority));
    }
}
