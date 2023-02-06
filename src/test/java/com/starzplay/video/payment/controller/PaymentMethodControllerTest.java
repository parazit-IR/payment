package com.starzplay.video.payment.controller;

import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.domain.PaymentPlan;
import com.starzplay.video.payment.enums.Currency;
import com.starzplay.video.payment.enums.Duration;
import com.starzplay.video.payment.model.PaymentMethodAllModel;
import com.starzplay.video.payment.model.PaymentPlanSlimModel;
import com.starzplay.video.payment.service.PaymentMethodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static com.starzplay.video.payment.enums.PaymentType.CREDIT_CARD;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentMethodController.class)
class PaymentMethodControllerTest {

    List<PaymentMethodAllModel> paymentMethodAllModels;
    PaymentMethod paymentMethodReturn;
    final String PAYMENT_METHOD_NAME = "alfa_lb";
    final Long PAYMENT_PLAN_ID = 72L;
    final String URI = "http://localhost:8080/api/v1.0/configuration/payment-methods";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    PaymentMethodService paymentMethodService;

    @BeforeEach
    void setUp(){
        List.of();
        PaymentMethodAllModel paymentMethodAllModel = new PaymentMethodAllModel();
        paymentMethodAllModel.setPaymentPlans(List.of(PaymentPlanSlimModel.builder()
                .id(72L)
                .netAmount(new BigDecimal("5.99"))
                .grossAmount(new BigDecimal("0"))
                .taxAmount(new BigDecimal("5.99"))
                .currency(Currency.USD)
                .duration(Duration.Month)
                .build()));
        paymentMethodAllModel.setPaymentType(CREDIT_CARD);
        paymentMethodAllModel.setName("alfa_lb");
        paymentMethodAllModel.setDisplayName("credit card");

        paymentMethodReturn = PaymentMethod.builder()
                .name("alfa_lb")
                .displayName("credit card")
                .paymentType(CREDIT_CARD)
                .paymentPlans(List.of(PaymentPlan.builder()
                        .id(72L)
                        .netAmount(new BigDecimal("5.99"))
                        .grossAmount(new BigDecimal("0"))
                        .taxAmount(new BigDecimal("5.99"))
                        .currency(Currency.USD)
                        .duration(Duration.Month)
                        .build())).build();

        Mockito.when(paymentMethodService.filterById(PAYMENT_PLAN_ID)).thenReturn(paymentMethodAllModels);
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URI).contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());

    }
}