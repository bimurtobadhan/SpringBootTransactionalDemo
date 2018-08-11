package com.bimurto.springBootTransactionalDemo.dao;

import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import com.bimurto.springBootTransactionalDemo.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveWithSameClassTrxTransactionalMethodCall(TestUser user){
        saveWithTransactionMethod(user);
        throw new RuntimeException();
    }

    @Transactional
    public void saveWithTransactionMethod(TestUser user){
        TestUser testUser = userRepository.save(user);
        log.info("Saved in DB. User {}", testUser);
    }

    @Transactional
    @Override
    public void saveWithTransactionMethodWithException(TestUser user){
        TestUser testUser = userRepository.save(user);
        log.info("Saved in DB. User {}", testUser);
        throw new RuntimeException();
    }


}
