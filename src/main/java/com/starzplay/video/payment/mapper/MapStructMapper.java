package com.starzplay.video.payment.mapper;


import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.model.PaymentMethodAllModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    List<PaymentMethodAllModel> paymentMethodsToPaymentMethodAllModels(List<PaymentMethod> paymentMethods);

    List<PaymentMethod> paymentMethodAllModelsToPaymentMethods(List<PaymentMethodAllModel> paymentMethodAllModels);

}
