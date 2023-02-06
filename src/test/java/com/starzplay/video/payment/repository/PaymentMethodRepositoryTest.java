package com.starzplay.video.payment.repository;

import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.domain.PaymentPlan;
import com.starzplay.video.payment.enums.Currency;
import com.starzplay.video.payment.enums.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static com.starzplay.video.payment.enums.PaymentType.CREDIT_CARD;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PaymentMethodRepositoryTest {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    final String PAYMENT_METHOD_NAME = "alfa_lb";
    final Long PAYMENT_PLAN_ID = 72L;

    @Test
    void findAllByName() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAllByName(PAYMENT_METHOD_NAME);
        assertEquals(1, paymentMethods.size());
        assertEquals(PAYMENT_METHOD_NAME, paymentMethods.get(0).getName());
        assertEquals(1, paymentMethods.size());
        assertEquals(2, paymentMethods.get(0).getPaymentPlans().size());

    }

    @Test
    void getReferenceByPaymentPlansIs() {
        List<PaymentMethod> paymentMethodAllModelsFilterById = paymentMethodRepository.getReferenceByPaymentPlansIs(PaymentPlan.builder().id(PAYMENT_PLAN_ID).build());
        assertEquals(1, paymentMethodAllModelsFilterById.size());
        assertEquals(1, paymentMethodAllModelsFilterById.get(0).getPaymentPlans().size());
        assertEquals(PAYMENT_PLAN_ID, paymentMethodAllModelsFilterById.get(0).getPaymentPlans().get(0).getId());
    }
}