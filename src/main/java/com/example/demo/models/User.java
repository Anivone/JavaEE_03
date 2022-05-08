package com.example.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "login should contain only letters and digits")
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 8, max = 20, message = "length of password should be between 8-20")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favorits", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> favoriteBooks;

    @ManyToMany(fetch = FetchType.EAGER)
    private String role;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}