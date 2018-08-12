package com.bimurto.springBootTransactionalDemo.repo;

import com.bimurto.springBootTransactionalDemo.domain.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<TestProduct , Long>{
    TestProduct findByProductName(String productName);
}
