package com.iprody.course.ms_payment.repository;


import com.iprody.course.ms_payment.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    }
