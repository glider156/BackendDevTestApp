package com.example.backenddev.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.backenddev.model.User;
import com.example.backenddev.model.User_;

@Stateless
public class UserRepository extends DevTestAbstractRepository<User> {

	@Inject
    private Logger log;
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UserRepository() {
		super(User.class);
	}

    public User findByEmail(String email)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(entityClass);
        Root<User> from = cq.from(entityClass);
        cq.select(from).where(cb.and(cb.equal(cb.lower(from.get(User_.email)), email.toLowerCase())));
        Query q = getEntityManager().createQuery(cq);
        try
        {
            return (User) q.getSingleResult();
        }
        catch (NoResultException e)
        {
            log.log(Level.INFO, "There is no user with email {0} ({1})", new Object[]{email, e.getMessage()});
        }
        return null;
    }
    
    public User findById(Long id)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(entityClass);
        Root<User> from = cq.from(entityClass);
        cq.select(from).where(cb.equal(from.get(User_.id), id));
        Query q = getEntityManager().createQuery(cq);
        return (User) q.getSingleResult();
    }
    
    
}
