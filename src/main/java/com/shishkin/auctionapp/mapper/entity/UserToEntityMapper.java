package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.user.UserEntity;
import com.shishkin.auctionapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserToEntityMapper {
    @Mapping(target = "password", expression = "java(encoder.encode(user.getPassword()))")
    UserEntity userToUserEntity(User user, PasswordEncoder encoder);
}
