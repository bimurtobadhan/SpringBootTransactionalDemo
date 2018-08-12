package com.bimurto.springBootTransactionalDemo;

import com.bimurto.springBootTransactionalDemo.dao.MixedDao;
import com.bimurto.springBootTransactionalDemo.dao.UserDao;
import com.bimurto.springBootTransactionalDemo.dao.UserDaoImplNoInterface;
import com.bimurto.springBootTransactionalDemo.domain.TestProduct;
import com.bimurto.springBootTransactionalDemo.domain.TestUser;
import com.bimurto.springBootTransactionalDemo.repo.ProductRepository;
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
    private UserDaoImplNoInterface userDaoImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MixedDao mixedDao;

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
        log.info("@Trnsactional is working!!!");
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
        log.info("@Trnsactional is not working!!!");
    }

    @Test
    public void shouldNotSave_whenCallTransactionalMethod_withException_withoutInterface(){
        String name = "Test3User";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        try {
            userDaoImpl.saveWithTransactionMethodWithException(user);
        } catch (Exception e) {
            log.error("Exception thrown. Ex msg {}", e.getMessage());
        }

        TestUser testUser = userRepository.findByName(name);
        Assert.assertNull("TestUser should be null", testUser);
        log.info("@Trnsactional not working!!!");
    }

    @Test
    public void shouldSave_whenCallNonTransactionalMethod_withInClassTransactionalMethod_withoutInterface(){
        String name = "Test4User";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        try {
            userDaoImpl.saveWithSameClassTrxTransactionalMethodCall(user);
        } catch (Exception e) {
            log.error("Exception thrown. Ex msg {}", e.getMessage());
        }

        TestUser testUser = userRepository.findByName(name);
        Assert.assertNotNull("TestUser should Not be null", testUser);
        log.info("@Trnsactional is not working!!!");
    }

    @Test
    public void shouldUpdate_whenCall_TransactionalMethod_direct(){
        String name = "Test5User";
        String updatedname = "Test5User"+"updated";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        userRepository.save(user);

        userDao.updateWithTransactionMethodWithNoExplicitSave(name);

        TestUser testUser = userRepository.findByName(updatedname);
        Assert.assertNotNull("TestUser should Not be null", testUser);
        log.info("Trnsactional is working!!!");
    }

    @Test
    public void shouldNotUpdate_whenCall_TransactionalMethod_WithInClassMethod(){
        String name = "Test6User";
        String updatedname = name + "updated";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        userRepository.save(user);

        userDao.updateWithSameClassTrxTransactionalMethodCallWithNoExplicitSave(name);

        TestUser testUser = userRepository.findByName(updatedname);
        Assert.assertNull("TestUser should be null", testUser);
        log.info("Trnsactional is not working!!!");
    }

    @Test
    public void shouldNotUpdateUser_WhenUpdateUserButSaveProduct_inNonTransactionalMethod(){
        String name = "Test7User";
        String productname = "productname";
        String updatedname = name + "updated";
        TestUser user = TestUser.builder()
                .name(name)
                .build();
        TestUser savedUser = userRepository.save(user);

        mixedDao.wrappermethodForupdateUserAndSaveProduct(savedUser);

        TestProduct product = productRepository.findByProductName(productname);
        Assert.assertEquals("Name should be same", productname, product.getProductName());

        TestUser testUser = userRepository.findByName(updatedname);
        Assert.assertNull("TestUser should be null", testUser);
        log.info("Trnsactional is not working!!!");
    }
}
