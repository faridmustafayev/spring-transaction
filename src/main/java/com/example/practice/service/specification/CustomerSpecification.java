package com.example.practice.service.specification;

import com.example.practice.dao.entity.CustomerEntity;
import com.example.practice.model.criteria.CustomerCriteria;
import com.example.practice.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.practice.dao.entity.CustomerEntity.Fields.age;
import static com.example.practice.dao.entity.CustomerEntity.Fields.name;
import static com.example.practice.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor(staticName = "of")
public class CustomerSpecification implements Specification<CustomerEntity> {
    private CustomerCriteria customerCriteria;

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(customerCriteria.getName(),
                        named -> cb.like(root.get(name), applyLikePattern(named)))
                .addNullSafety(customerCriteria.getAgeFrom(),
                        ageFrom -> cb.greaterThanOrEqualTo(root.get(age), ageFrom))
                .addNullSafety(customerCriteria.getAgeTo(),
                        ageTo -> cb.lessThanOrEqualTo(root.get(age), ageTo))
                .build();

        return cb.and(predicates);
    }
}
