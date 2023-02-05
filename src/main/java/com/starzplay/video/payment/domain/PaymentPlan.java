package com.starzplay.video.payment.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.starzplay.video.payment.enums.Currency;
import com.starzplay.video.payment.enums.Duration;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "payment_plan")
@Setter
@Getter
@Audited
@AuditOverride(forClass = Auditable.class)
public class PaymentPlan extends Auditable {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "payment_plan")
//    @SequenceGenerator(name = "payment_plan", sequenceName = "payment_plan_seq", schema = "public", allocationSize = 1)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "payment_plan", sequenceName = "hibernate_sequence", schema = "public",  allocationSize = 1)
    private Long id;

    @Column(name = "net_amount", precision = 11, scale = 2)
    private BigDecimal netAmount;

    @Column(name = "tax_amount", precision = 11, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "gross_amount", precision = 11, scale = 2)
    private BigDecimal grossAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration")
    private Duration duration;

    @JsonBackReference
    @ManyToMany(mappedBy = "paymentPlans")
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        paymentMethod.getPaymentPlans().add(this);
    }

    public void removePaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethods.remove(paymentMethod);
        paymentMethod.getPaymentPlans().remove(this);
    }

    public void removePaymentMethods() {
        Iterator<PaymentMethod> iterator = this.paymentMethods.iterator();
        while (iterator.hasNext()) {
            PaymentMethod paymentMethod = iterator.next();
            paymentMethod.setPaymentPlans(new ArrayList<>());
            iterator.remove();
        }
    }
}
