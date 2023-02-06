package com.starzplay.video.payment.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.starzplay.video.payment.config.MoneySerializer;
import com.starzplay.video.payment.enums.Currency;
import com.starzplay.video.payment.enums.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPlanSlimModel {

    private Long id;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal netAmount;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal taxAmount;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal grossAmount;

    private Currency currency;

    private Duration duration;

}
