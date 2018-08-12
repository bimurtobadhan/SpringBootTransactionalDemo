package com.bimurto.springBootTransactionalDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEST_PRODUCT")
public class TestProduct {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "PRODUCT_NAME")
    String productName;
}
