package com.trankhaihung.ecommerce.repository;

import com.trankhaihung.ecommerce.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    UserLogin getUserLoginByUsername(String username);
}
