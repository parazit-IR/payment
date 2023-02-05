package com.starzplay.video.payment.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodAllModelInput {
    List<PaymentMethodAllModel> paymentMethods;
}
