package com.jaydeep.blogapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaydeep.blogapplication.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


	Optional<User> findByEmail(String username);

}
