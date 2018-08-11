package com.bimurto.springBootTransactionalDemo;

import com.bimurto.springBootTransactionalDemo.dao.UserDao;
import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import com.bimurto.springBootTransactionalDemo.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTransactionalDemoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads(){
        log.info("Spring Boot Context Has Been loaded!!!");
    }

    @Test
    public void shouldNotSave_whenCallTransactionalMethod_withException(){
        String name = "Test1User";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        try {
            userDao.saveWithTransactionMethodWithException(user);
        } catch (Exception e) {
            log.error("Exception thrown. Ex msg {}", e.getMessage());
        }

        TestUser testUser = userRepository.findByName(name);
        Assert.assertNull("TestUser should be null", testUser);
        log.info("Trnsactional is working!!!");
    }

    @Test
    public void shouldSave_whenCallNonTransactionalMethod_withInClassTransactionalMethod(){
        String name = "Test2User";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        try {
            userDao.saveWithSameClassTrxTransactionalMethodCall(user);
        } catch (Exception e) {
            log.error("Exception thrown. Ex msg {}", e.getMessage());
        }

        TestUser testUser = userRepository.findByName(name);
        Assert.assertNotNull("TestUser should Not be null", testUser);
        log.info("Trnsactional is not working!!!");
    }
}
