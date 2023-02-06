package com.starzplay.video.payment.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.starzplay.video.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@NamedEntityGraph(name = "payment-method-graph-with-payment-plans",
        attributeNodes = {@NamedAttributeNode("name"), @NamedAttributeNode("displayName"), @NamedAttributeNode("paymentType"), @NamedAttributeNode(value = "paymentPlans", subgraph = "payment-plan-subgraph"),},
        subgraphs = {@NamedSubgraph(name = "payment-plan-subgraph", attributeNodes = {@NamedAttributeNode("netAmount"), @NamedAttributeNode("taxAmount"), @NamedAttributeNode("grossAmount"), @NamedAttributeNode("currency"), @NamedAttributeNode("duration")})})
@Entity
@Table(name = "payment_method")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditOverride(forClass = Auditable.class)
public class PaymentMethod extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "payment_method", sequenceName = "hibernate_sequence", schema = "public", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nullable
    @Column(name = "name", nullable = false)
    private String name;

    @Nullable
    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Nullable
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "payment_method_payment_plan",
            joinColumns = @JoinColumn(name = "payment_method_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "payment_plan_id", nullable = false))
    private List<PaymentPlan> paymentPlans = new ArrayList<>();

    public void addPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlans.add(paymentPlan);
        paymentPlan.getPaymentMethods().add(this);
    }

    public void addPaymentPlans(List<PaymentPlan> paymentPlans) {
        this.paymentPlans.addAll(paymentPlans);
        paymentPlans.stream().forEach(paymentPlan -> paymentPlan.getPaymentMethods().add(this));
    }

    public void removePaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlans.remove(paymentPlan);
        paymentPlan.getPaymentMethods().remove(this);
    }

    public void removePaymentPlans() {
        Iterator<PaymentPlan> iterator = this.paymentPlans.iterator();
        while (iterator.hasNext()) {
            PaymentPlan paymentPlan = iterator.next();
            paymentPlan.setPaymentMethods(new ArrayList<>());
            iterator.remove();
        }
    }
}
