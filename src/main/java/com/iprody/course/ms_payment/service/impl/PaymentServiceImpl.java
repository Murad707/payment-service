package com.iprody.course.ms_payment.service.impl;

import com.iprody.course.ms_payment.dto.RequestDto;
import com.iprody.course.ms_payment.dto.ResponseDto;
import com.iprody.course.ms_payment.exception.Error;
import com.iprody.course.ms_payment.exception.ServiceException;
import com.iprody.course.ms_payment.mapper.PaymentMapper;
import com.iprody.course.ms_payment.model.PaymentEntity;
import com.iprody.course.ms_payment.repository.PaymentRepository;
import com.iprody.course.ms_payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public List<PaymentEntity> getAllPayments() {
      List<PaymentEntity> entity= paymentRepository.findAll();
       if (entity.isEmpty()){
           log.error("No Data in Database");
           throw new ServiceException(Error.PAYMENT_NOT_FOUND);
       }else
           log.info("Found Payments in Database "+entity);
        return entity;
    }

    @Override
    public ResponseDto getById (Integer id) {
       log.info("ActionLog.getById.start id:{}",id);
       Optional<PaymentEntity> entity= paymentRepository.findById(Long.valueOf(id));
       if (entity.isEmpty()){
           log.error("ActionLog.getById.start id:{}",id);
           throw new ServiceException(Error.PAYMENT_NOT_FOUND,String.valueOf(id));
       }
      ResponseDto responseDto= paymentMapper.toDto(entity.get());
        log.info("ActionLog.getById.success id:{}",id);
        return responseDto;
    }

    @Override
    public void create(RequestDto dto) {
        log.info("ActionLog.create.start ");
       PaymentEntity entity = paymentMapper.toEntity(dto);
        if (entity==null){
            log.error("ActionLog.create.error ");
            throw new ServiceException(Error.BAD_REQUEST, String.valueOf(dto.getAmount()));
        }
       paymentRepository.save(entity);

    }

    @Override
    public void delete(Integer id) {
       paymentRepository.deleteById(Long.valueOf(id));

    }

    @Override
    public ResponseDto update(Integer id, RequestDto dto) {
     PaymentEntity entity =
             paymentRepository.findById(id.longValue()).orElseThrow(
               ()-> {
                   log.error("ActionLog.update.error ");
               return new ServiceException(Error.PAYMENT_NOT_FOUND,id);
               }
               );

      paymentMapper.updateFromDto(dto,entity);

       PaymentEntity saved= paymentRepository.save(entity);
        return paymentMapper.toDto(saved);
    }
}
