package vn.com.ibss.au.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.ibss.au.entity.User;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
}
