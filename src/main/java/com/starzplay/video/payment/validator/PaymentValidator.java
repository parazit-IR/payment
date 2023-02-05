package com.starzplay.video.payment.validator;

import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.enums.ExceptionCode;
import com.starzplay.video.payment.exception.PaymentException;
import com.starzplay.video.payment.exception.PaymentExceptionType;
import com.starzplay.video.payment.model.PaymentMethodAllModel;
import com.starzplay.video.payment.util.SpringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PaymentValidator implements BusinessValidator<PaymentMethod> {

    private final SpringUtil springUtil;

    @Override
    public void validate(PaymentMethod paymentMethod) throws BindingException {

        BindingException bindingException = new BindingException();

        if (paymentMethod != null) {
            if (paymentMethod.getId() != null)
                validateUpdate(paymentMethod, bindingException);
            else validateCreate(paymentMethod, bindingException);
        }

        if (bindingException.getErrors().size() > 0)
            throw bindingException;
    }

    private void validateCreate(PaymentMethod paymentMethodAllModel, BindingException bindingException) {
        if (paymentMethodAllModel.getPaymentPlans().size() == 0){
            bindingException.addPaymentException(springUtil.getMessage("PaymentPlanIsNecessary", null));
        }
    }

    private void validateUpdate(PaymentMethod paymentMethodAllModel, BindingException bindingException) {

    }
}
