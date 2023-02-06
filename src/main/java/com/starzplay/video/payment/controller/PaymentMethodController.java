package com.starzplay.video.payment.controller;


import com.starzplay.video.payment.exception.PaymentException;
import com.starzplay.video.payment.model.PaymentMethodAllModelInput;
import com.starzplay.video.payment.service.PaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.starzplay.video.payment.exception.PaymentExceptionType.DEFAULT_EXCEPTION;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/configuration/payment-methods")
public class PaymentMethodController extends BaseController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<?> list(@Nullable @RequestParam String name,
                                  @Nullable @RequestParam Long id) {
        log.debug("call list by parameter name :{}, id :{}", name, id);
        if (name == null && id == null)
            return success(paymentMethodService.list());
        else if (name != null && id == null)
            return success(paymentMethodService.filterByName(name));
        else if (name == null && id != null) {
            try {
                return success(paymentMethodService.filterById(id));
            } catch (Exception e) {
                return failure(e);
            }
        }
        return failure(new PaymentException(DEFAULT_EXCEPTION));
    }

    @PostMapping
    public ResponseEntity<PaymentMethodAllModelInput> save(@Valid @RequestBody PaymentMethodAllModelInput paymentMethodAllModelInput) {
        log.debug("call save paymentMethodAllModelInput :{}", paymentMethodAllModelInput);
        try {
            return success(paymentMethodService.create(paymentMethodAllModelInput));
        } catch (Exception c) {
            return failure(c);
        }
    }

    @PutMapping
    public ResponseEntity<PaymentMethodAllModelInput> update(@RequestParam(name = "payment-methods") List<Long> paymentMethodIds,
                                                             @RequestBody PaymentMethodAllModelInput paymentMethodAllModelInput) {
        log.debug("call update paymentMethodIds :{}, paymentMethodAllModelInput :{}", paymentMethodIds, paymentMethodAllModelInput);
        try {
            return success(paymentMethodService.update(paymentMethodIds, paymentMethodAllModelInput));
        } catch (Exception c) {
            return failure(c);
        }

    }


}
