package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.user.UserEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("SELECT * from users where username = ?0 ALLOW FILTERING")
    UserEntity findByUsername(String username);
}
