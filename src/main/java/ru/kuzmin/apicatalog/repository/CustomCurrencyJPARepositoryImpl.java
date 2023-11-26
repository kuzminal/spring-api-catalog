package ru.kuzmin.apicatalog.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kuzmin.apicatalog.domain.entity.Currency;

import java.util.List;

@Repository
@Transactional
public class CustomCurrencyJPARepositoryImpl implements CustomCurrencyJPARepository {
    EntityManager em;

    public CustomCurrencyJPARepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<Currency> myCustomFindMethod(String code) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Currency> cq = cb.createQuery(Currency.class);
        Root<Currency> currency = cq.from(Currency.class);
        Predicate codePredicate = cb.equal(currency.get("code"), code);
        cq.where(codePredicate);
        TypedQuery<Currency> query = em.createQuery(cq);
        return query.getResultList();
    }
}
