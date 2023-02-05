package com.starzplay.video.payment.service;


import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.domain.PaymentPlan;
import com.starzplay.video.payment.exception.PaymentException;
import com.starzplay.video.payment.mapper.MapStructMapper;
import com.starzplay.video.payment.model.PaymentMethodAllModel;
import com.starzplay.video.payment.model.PaymentMethodAllModelInput;
import com.starzplay.video.payment.repository.PaymentMethodRepository;
import com.starzplay.video.payment.repository.PaymentPlanRepository;
import com.starzplay.video.payment.validator.PaymentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.starzplay.video.payment.exception.PaymentExceptionType.PAYMENT_METHOD_EXISTS_EXCEPTION;


@Slf4j
@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentPlanRepository paymentPlanRepository;
    private final MapStructMapper mapStructMapper;
    private final PaymentValidator paymentValidator;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, PaymentPlanRepository paymentPlanRepository, MapStructMapper mapStructMapper, PaymentValidator paymentValidator) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentPlanRepository = paymentPlanRepository;
        this.mapStructMapper = mapStructMapper;
        this.paymentValidator = paymentValidator;
    }

    @Transactional(readOnly = true)
    public List<PaymentMethodAllModel> list() {
        List<PaymentMethodAllModel> paymentMethodAllModels = mapStructMapper.paymentMethodsToPaymentMethodAllModels(paymentMethodRepository.findAll());
        log.debug("Filter paymentMethodAllModels : {}", paymentMethodAllModels);
        return paymentMethodAllModels;
    }

    @Transactional(readOnly = true)
    public List<PaymentMethodAllModel> filter(String name) {
        List<PaymentMethodAllModel> paymentMethodAllModels = mapStructMapper.paymentMethodsToPaymentMethodAllModels(paymentMethodRepository.findAllByName(name));
        log.debug("Filter paymentMethodAllModels : {}", paymentMethodAllModels);
        return paymentMethodAllModels;
    }

    @Transactional(readOnly = true)
    public List<PaymentMethodAllModel> filter(Long paymentPlanId) {
        PaymentPlan paymentPlan1 = new PaymentPlan();
        paymentPlan1.setId(paymentPlanId);
        List<PaymentMethod> paymentMethods = paymentMethodRepository.getReferenceByPaymentPlansIs(paymentPlan1);
        List<PaymentMethodAllModel> paymentMethodAllModels = mapStructMapper.paymentMethodsToPaymentMethodAllModels(paymentMethods);
        log.debug("Filter paymentMethodAllModels : {}", paymentMethodAllModels);
        return paymentMethodAllModels;
    }


    @Transactional(rollbackFor = Exception.class)
    public PaymentMethodAllModelInput create(PaymentMethodAllModelInput paymentMethodAllModels) throws PaymentException {

        List<PaymentMethod> paymentMethods = mapStructMapper.paymentMethodAllModelsToPaymentMethods(paymentMethodAllModels.getPaymentMethods());
        paymentMethods.stream().forEach(p -> paymentValidator.validate(p));

        Optional<PaymentMethod> first = paymentMethods.stream().filter(p ->
                paymentMethodRepository.existsByNameAndDisplayNameAndPaymentTypeAndPaymentPlansIn(p.getName(), p.getDisplayName(), p.getPaymentType(), p.getPaymentPlans())).findFirst();
        if (first.isPresent()) {
            log.error("Error when create paymentMethodS and exists in db, paymentMethodStored : {}", first);
            throw new PaymentException(PAYMENT_METHOD_EXISTS_EXCEPTION);
        }

        PaymentMethodAllModelInput paymentMethodAllModelInput = PaymentMethodAllModelInput.builder()
                .paymentMethods(mapStructMapper.paymentMethodsToPaymentMethodAllModels(paymentMethodRepository.saveAll(paymentMethods)))
                .build();
        log.debug("Created paymentMethodAllModelInput : {}", paymentMethodAllModelInput);
        return paymentMethodAllModelInput;
    }

    @Transactional(rollbackFor = Exception.class)
    public PaymentMethodAllModelInput update(List<Long> paymentMethodIds, PaymentMethodAllModelInput paymentMethodAllModelInput) throws Exception {
        paymentMethodRepository.deleteAllById(paymentMethodIds);
        PaymentMethodAllModelInput resultPaymentMethod = create(paymentMethodAllModelInput);
        log.debug("Updated resultPaymentMethod : {}", resultPaymentMethod);
        return resultPaymentMethod;
    }

}
