package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.mapper.UserToEntityMapper;
import com.shishkin.auctionapp.model.User;
import com.shishkin.auctionapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final UserToEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(User user) {
        userRepository.save(mapper.userToUserEntity(user, passwordEncoder));
    }

}
