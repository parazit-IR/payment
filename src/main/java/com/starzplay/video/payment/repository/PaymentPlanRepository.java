package com.starzplay.video.payment.repository;

import com.starzplay.video.payment.domain.PaymentPlan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"paymentMethods"})
    Optional<PaymentPlan> findById(Long aLong);

}
