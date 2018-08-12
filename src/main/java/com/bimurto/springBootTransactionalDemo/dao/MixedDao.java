package com.bimurto.springBootTransactionalDemo.dao;

import com.bimurto.springBootTransactionalDemo.domain.TestUser;

public interface MixedDao {
    void wrappermethodForupdateUserAndSaveProduct(TestUser user);

    void updateUserAndSaveProduct(TestUser user);
}
