package com.starzplay.video.payment.service;

import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.domain.PaymentPlan;
import com.starzplay.video.payment.enums.Currency;
import com.starzplay.video.payment.enums.Duration;
import com.starzplay.video.payment.exception.PaymentException;
import com.starzplay.video.payment.model.PaymentMethodAllModel;
import com.starzplay.video.payment.model.PaymentMethodAllModelInput;
import com.starzplay.video.payment.model.PaymentPlanSlimModel;
import com.starzplay.video.payment.repository.PaymentMethodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static com.starzplay.video.payment.enums.PaymentType.CREDIT_CARD;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaymentMethodServiceTest {

    @Autowired
    PaymentMethodService paymentMethodService;

    @MockBean
    PaymentMethodRepository paymentMethodRepository;

    final String PAYMENT_METHOD_NAME = "alfa_lb";
    final Long PAYMENT_PLAN_ID = 72L;

    PaymentMethod paymentMethodFilterByName;
    PaymentMethod paymentMethodFilterById;

    @BeforeEach
    void setup() {

        paymentMethodFilterByName = PaymentMethod.builder()
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
                                .build(),
                        PaymentPlan.builder()
                                .id(54L)
                                .netAmount(new BigDecimal("10"))
                                .grossAmount(new BigDecimal("0"))
                                .taxAmount(new BigDecimal("10"))
                                .currency(Currency.SAR)
                                .duration(Duration.Week)
                                .build()))
                .build();

        paymentMethodFilterById = PaymentMethod.builder()
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

        Mockito.when(paymentMethodRepository.findAllByName(PAYMENT_METHOD_NAME)).thenReturn(List.of(paymentMethodFilterByName));
        Mockito.when(paymentMethodRepository.getReferenceByPaymentPlansIs(PaymentPlan.builder().id(PAYMENT_PLAN_ID).build())).thenReturn(List.of(paymentMethodFilterById));
        Mockito.when(paymentMethodRepository.saveAll(List.of(paymentMethodFilterById))).thenReturn(List.of(paymentMethodFilterById));
//        Mockito.when(paymentMethodRepository.existsByNameAndDisplayNameAndPaymentTypeAndPaymentPlansIn("alfa_lb", "credit card", CREDIT_CARD,List.of(PaymentPlan.builder()
//                .id(PAYMENT_PLAN_ID)
//                .netAmount(new BigDecimal("5.99"))
//                .grossAmount(new BigDecimal("0"))
//                .taxAmount(new BigDecimal("5.99"))
//                .currency(Currency.USD)
//                .duration(Duration.Month)
//                .build()))).thenReturn(true);
    }

//    @Test
//    void list() {
//        List<PaymentMethodAllModel> paymentMethodAllModels = paymentMethodService.list();
//        assertEquals(3, paymentMethodAllModels.size());
//    }

    @Test
    void filterByName() {
        List<PaymentMethodAllModel> paymentMethodAllModelsFilterByName = paymentMethodService.filterByName(PAYMENT_METHOD_NAME);
        assertEquals(1, paymentMethodAllModelsFilterByName.size());
        assertEquals(PAYMENT_METHOD_NAME, paymentMethodAllModelsFilterByName.get(0).getName());
    }

    @Test
    void filterById() {
        // can not fetch other side of relation in test
//        List<PaymentMethodAllModel> paymentMethodAllModelsFilterById = paymentMethodService.filterById(PAYMENT_PLAN_ID);
//        assertEquals(1, paymentMethodAllModelsFilterById.size());
//        assertEquals(2, paymentMethodAllModelsFilterById.get(0).getPaymentPlans().size());
//        assertEquals(PAYMENT_PLAN_ID, paymentMethodAllModelsFilterById.get(0).getId());
    }

    @Test()
    void create() throws PaymentException {
        PaymentMethodAllModelInput paymentMethodAllModelInput = new PaymentMethodAllModelInput();
        PaymentMethodAllModel paymentMethodAllModel = new PaymentMethodAllModel();
        PaymentPlan paymentPlan = paymentMethodFilterById.getPaymentPlans().get(0);
        paymentMethodAllModel.setPaymentPlans(List.of(PaymentPlanSlimModel.builder()
                .id(paymentPlan.getId())
                .currency(paymentPlan.getCurrency())
                .duration(paymentPlan.getDuration())
                .grossAmount(paymentPlan.getGrossAmount())
                .netAmount(paymentPlan.getNetAmount())
                .taxAmount(paymentPlan.getTaxAmount())
                .build()));
        paymentMethodAllModel.setName(paymentMethodFilterById.getName());
        paymentMethodAllModel.setDisplayName(paymentMethodFilterById.getDisplayName());
        paymentMethodAllModel.setPaymentType(paymentMethodFilterById.getPaymentType());


        paymentMethodAllModelInput.setPaymentMethods(List.of(paymentMethodAllModel));

//        paymentMethodService.create(paymentMethodAllModelInput);
//        paymentMethodService.create(paymentMethodAllModelInput);
//        PaymentException paymentException = assertThrows(PaymentException.class, () -> paymentMethodService.create(paymentMethodAllModelInput));
//        assertEquals(PaymentExceptionType.PAYMENT_METHOD_EXISTS_EXCEPTION.getErrorMessageEn(), paymentException.getMessage());
    }

    @Test
    void update() {
    }
}