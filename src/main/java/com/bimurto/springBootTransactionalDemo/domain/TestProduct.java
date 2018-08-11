package com.bimurto.springBootTransactionalDemo.domain;

import javax.persistence.*;

@Entity
@Table(name = "TEST_PRODUCT")
public class TestProduct {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "PRODUCT_NAME")
    String productName;
}
