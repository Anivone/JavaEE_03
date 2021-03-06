package com.example.demo.services;

import com.example.demo.model.Book;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void signup(User user) {

        Optional<User> usrByUsrnam = userRepository.findUserByUsernameIgnoreCase(user.getUsername());
        Optional<User> usrByMail = userRepository.findUserByEmailIgnoreCase(user.getEmail());

        if (usrByUsrnam.isPresent() || usrByMail.isPresent()) {
            throw new IllegalArgumentException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "USER")));

        userRepository.save(user);

    }

    private static final Logger logger = Logger.getGlobal();

    @Transactional
    public void login(String username, String password) {
        Optional<User> foundUser = userRepository.findUserByUsernameIgnoreCase(username);
        if (!foundUser.isPresent() || !bCryptPasswordEncoder.matches(password, foundUser.get().getPassword())) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void addFavoriteBook(Optional<User> user, Book book) {
        user.get().getFavoriteBooks().add(book);
        book.getLikedByUsers().add(user.get());
        userRepository.save(user.get());
    }

    @Transactional
    public void removeFromFavorites(Optional<User> user, Book book) {
        user.get().getFavoriteBooks().remove(book);
        book.getLikedByUsers().remove(user.get());
        userRepository.save(user.get());
    }
}