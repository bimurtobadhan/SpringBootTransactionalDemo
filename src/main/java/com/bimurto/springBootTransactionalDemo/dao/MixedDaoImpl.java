package com.bimurto.springBootTransactionalDemo.dao;

import com.bimurto.springBootTransactionalDemo.domain.TestProduct;
import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import com.bimurto.springBootTransactionalDemo.repo.ProductRepository;
import com.bimurto.springBootTransactionalDemo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MixedDaoImpl implements MixedDao{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void wrappermethodForupdateUserAndSaveProduct(TestUser user){
        updateUserAndSaveProduct(user);
    }

    @Transactional
    @Override
    public void updateUserAndSaveProduct(TestUser user){
        String updatedname = user.getName() + "updated";
        user.setName(updatedname);

        TestProduct product = TestProduct.builder()
                .productName(user.getName())
                .build();

        productRepository.save(product);
    }
}
