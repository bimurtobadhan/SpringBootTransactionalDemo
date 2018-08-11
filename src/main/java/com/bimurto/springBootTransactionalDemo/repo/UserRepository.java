package com.bimurto.springBootTransactionalDemo.repo;

import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TestUser, Long>{
    TestUser findByName(String name);
}
