package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.user.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends EntityRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
