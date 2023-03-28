package com.github.amitsureshchandra.accountservice.repo;

import com.github.amitsureshchandra.accountservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
