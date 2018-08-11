package com.bimurto.springBootTransactionalDemo.dao;

import com.bimurto.springBootTransactionalDemo.domain.TestUser;

public interface UserDao {
    void saveWithSameClassTrxTransactionalMethodCall(TestUser user);

    void saveWithTransactionMethodWithException(TestUser user);
}
