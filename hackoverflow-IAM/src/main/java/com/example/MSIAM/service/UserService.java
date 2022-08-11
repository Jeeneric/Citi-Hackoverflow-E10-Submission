package com.example.MSIAM.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MSIAM.entity.User;
import com.example.MSIAM.exceptions.UserAlreadyExistsException;
import com.example.MSIAM.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Updates a user
     * 
     * @param user
     * @return updated user
     */
    public User update(User user) {
        return userRepo.save(user);
    }

    /**
     * Creates a new user
     * 
     * @param user
     * @return newly created user
     */
    public User createUser(User user) {
        Optional<User> search = userRepo.findByUsername(user.getUsername());

        if (search.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        return userRepo.save(user);
    }

    public User loadUserEntityByUsername(String username) throws UsernameNotFoundException {
        Optional<User> search = userRepo.findByUsername(username);
        User user;
        try {
            user = search.get();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> search = userRepo.findByUsername(username);
        User user = search.get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }

}
