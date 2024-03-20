package com.deliveryproject.repositories;

import com.deliveryproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String>  {
    UserDetails findByEmail(String email);
}
