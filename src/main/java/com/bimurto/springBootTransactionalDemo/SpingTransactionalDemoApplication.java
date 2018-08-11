package com.bimurto.springBootTransactionalDemo;

import com.bimurto.springBootTransactionalDemo.dao.UserDao;
import com.bimurto.springBootTransactionalDemo.dao.UserDaoImplNoInterface;
import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class SpingTransactionalDemoApplication implements ApplicationRunner{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoImplNoInterface userDaoImpl;

    private Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(SpingTransactionalDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        userDaoImpl.saveWithTransactionMethodWithException(TestUser.builder()
//                            .name("Name" + random.nextInt())
//                            .build()
//                );
    }
}
