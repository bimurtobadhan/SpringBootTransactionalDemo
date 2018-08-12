package com.bimurto.springBootTransactionalDemo.dao;

import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {
    void saveWithSameClassTrxTransactionalMethodCall(TestUser user);

    void saveWithTransactionMethodWithException(TestUser user);

    void updateWithTransactionMethodWithNoExplicitSave(String name);

    void updateWithSameClassTrxTransactionalMethodCallWithNoExplicitSave(String name);
}
