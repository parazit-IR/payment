package com.starzplay.video.payment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.starzplay.video.payment.enums.PaymentType;
import lombok.Data;

import java.util.List;

@Data
@JsonRootName("paymentMethods")
public class PaymentMethodAllModel {

    @JsonIgnore
    private Long id;

    private String name;

    private String displayName;

    private PaymentType paymentType;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
    @JsonProperty("paymentPlans")
    private List<PaymentPlanSlimModel> paymentPlans;

}
