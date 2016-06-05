package com.example.backenddev.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.backenddev.model.Password;
import com.example.backenddev.model.Password_;
import com.example.backenddev.model.User;

@Stateless
public class PasswordRepository extends DevTestAbstractRepository<Password> {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PasswordRepository() {
		super(Password.class);
	}
	
    public Password findByUser(User user)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Password> cq = cb.createQuery(entityClass);
        Root<Password> from = cq.from(entityClass);
        cq.select(from).where(cb.equal(from.get(Password_.user), user));
        Query q = getEntityManager().createQuery(cq);
        return (Password) q.getSingleResult();
    }
	
}
