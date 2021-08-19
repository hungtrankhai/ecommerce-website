package com.trankhaihung.ecommerce.repository;

import com.trankhaihung.ecommerce.entity.Role;
import com.trankhaihung.ecommerce.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
