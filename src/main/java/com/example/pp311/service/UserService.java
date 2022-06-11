package com.example.pp311.service;


import com.example.pp311.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User save(User user);

    public Optional<User> find(long id);

    public List<User> getAll();

    public void delete(User user);
}
