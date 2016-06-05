package com.example.backenddev.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.backenddev.model.ActivationCode;
import com.example.backenddev.model.ActivationCode_;
import com.example.backenddev.model.User;

@Stateless
public class ActivationCodeRepository extends DevTestAbstractRepository<ActivationCode> {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ActivationCodeRepository() {
		super(ActivationCode.class);
	}
	
    public ActivationCode findByUser(User user)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ActivationCode> cq = cb.createQuery(entityClass);
        Root<ActivationCode> from = cq.from(entityClass);
        cq.select(from).where(cb.equal(from.get(ActivationCode_.user), user));
        Query q = getEntityManager().createQuery(cq);
        return (ActivationCode) q.getSingleResult();
    }

    public ActivationCode findByCode(String code)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ActivationCode> cq = cb.createQuery(entityClass);
        Root<ActivationCode> from = cq.from(entityClass);
        cq.select(from).where(cb.equal(from.get(ActivationCode_.code), code));
        Query q = getEntityManager().createQuery(cq);
        return (ActivationCode) q.getSingleResult();
    }
    
}
