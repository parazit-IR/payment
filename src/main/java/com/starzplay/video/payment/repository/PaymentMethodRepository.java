package com.starzplay.video.payment.repository;


import com.starzplay.video.payment.domain.PaymentMethod;
import com.starzplay.video.payment.domain.PaymentPlan;
import com.starzplay.video.payment.enums.PaymentType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findAllByName(String name);

    boolean existsByNameAndDisplayNameAndPaymentTypeAndPaymentPlansIn(String name, String displayName, PaymentType paymentType, List<PaymentPlan> paymentPlans);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"paymentPlans"})
    List<PaymentMethod> getReferenceByPaymentPlansIs(PaymentPlan paymentPlan);

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteAllById(Iterable<? extends Long> longs);
}
