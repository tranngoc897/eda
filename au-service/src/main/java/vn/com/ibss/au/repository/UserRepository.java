package vn.com.ibss.au.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.com.ibss.au.entity.User;

import java.util.Optional;

public interface UserRepository extends  JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findById(Integer id);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Override
    void delete(User user);

    User findUserByEmail(String email);

    boolean existsByUsername(String username);
}
