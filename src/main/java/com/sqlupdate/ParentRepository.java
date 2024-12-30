package com.sqlupdate;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.jpa.SpecHints;

@ApplicationScoped
public class ParentRepository {

    private final EntityManager entityManager;

    ParentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void persist(ChildJPA childJPA) {
        entityManager.persist(childJPA);
    }

    public void update(ChildJPA childJPA) {
        Session session = entityManager.unwrap(Session.class);
        session.clear();
        session.update(childJPA);
    }

    public ChildJPA queryFirst() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChildJPA> query = criteriaBuilder.createQuery(ChildJPA.class);
        Root<ChildJPA> root = query.from(ChildJPA.class);
        Predicate predicate = root.get(ObjectJPA_.key).get(KeyJPA_.xId).in(1L);
        CriteriaQuery<ChildJPA> criteriaQuery = query.select(root)
                .where(predicate);
        ChildJPA singleResult = entityManager.createQuery(criteriaQuery)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .setHint(SpecHints.HINT_SPEC_LOCK_TIMEOUT, LockOptions.NO_WAIT)
                .getSingleResult();
        entityManager.detach(singleResult);
        return singleResult;
    }

}