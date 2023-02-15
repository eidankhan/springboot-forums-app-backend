package com.sprinboot.jwt.app.repository;
import com.sprinboot.jwt.app.model.User;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}