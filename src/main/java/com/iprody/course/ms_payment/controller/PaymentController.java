package com.iprody.course.ms_payment.controller;


import com.iprody.course.ms_payment.dto.RequestDto;
import com.iprody.course.ms_payment.dto.ResponseDto;
import com.iprody.course.ms_payment.model.PaymentEntity;
import com.iprody.course.ms_payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    @ResponseStatus(OK)
    public List<PaymentEntity> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseDto getPaymentById(@PathVariable Integer id) {
        return paymentService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void createPayment(@RequestBody RequestDto requestDto) {
        paymentService.create(requestDto);

    }

    @DeleteMapping("/delete/{id}")
    public void deletePaymentById(@PathVariable Integer id) {
        paymentService.delete(id);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(OK)
    public ResponseDto updatePaymentById(@PathVariable Integer id,
                                         @RequestBody RequestDto requestDto) {
        return paymentService.update(id, requestDto);
    }
}
