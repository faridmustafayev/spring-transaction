package com.example.practice.service.specification;

import com.example.practice.dao.entity.InvoiceEntity;
import com.example.practice.model.criteria.InvoiceCriteria;
import com.example.practice.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.practice.dao.entity.InvoiceEntity.Fields.amount;
import static com.example.practice.dao.entity.InvoiceEntity.Fields.status;
import static com.example.practice.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor(staticName = "of")
public class InvoiceSpecification implements Specification<InvoiceEntity> {
    private InvoiceCriteria invoiceCriteria;

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(invoiceCriteria.getStatus(),
                        customerAndInvoiceStatus -> cb.like(root.get(status), applyLikePattern(customerAndInvoiceStatus.name())))
                .addNullSafety(invoiceCriteria.getAmountFrom(),
                        amountFrom -> cb.greaterThanOrEqualTo(root.get(amount), amountFrom))
                .addNullSafety(invoiceCriteria.getAmountTo(),
                        amountTo -> cb.lessThanOrEqualTo(root.get(amount), amountTo))
                .build();

        return cb.and(predicates);
    }
}
