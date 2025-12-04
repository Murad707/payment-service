package com.iprody.course.ms_payment.service;

import com.iprody.course.ms_payment.dto.RequestDto;
import com.iprody.course.ms_payment.dto.ResponseDto;
import com.iprody.course.ms_payment.model.PaymentEntity;

import java.util.List;

public interface PaymentService {
    List<PaymentEntity> getAllPayments();

    ResponseDto getById(Integer id);

    void create(RequestDto dto);

    void delete(Integer id);

    ResponseDto update(Integer id, RequestDto dto);

}
