package com.iprody.course.ms_payment.mapper;


import com.iprody.course.ms_payment.dto.RequestDto;
import com.iprody.course.ms_payment.dto.ResponseDto;
import com.iprody.course.ms_payment.model.PaymentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEntity toEntity(RequestDto dto);

    ResponseDto toDto(PaymentEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(RequestDto dto, @MappingTarget PaymentEntity entity);

    List<ResponseDto> toDtoList(List<PaymentEntity> entities);


}