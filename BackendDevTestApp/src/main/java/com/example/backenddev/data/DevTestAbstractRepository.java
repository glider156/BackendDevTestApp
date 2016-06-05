
package com.example.backenddev.data;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class DevTestAbstractRepository<T>
{
    protected Class<T> entityClass;

    public DevTestAbstractRepository(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity)
    {
        getEntityManager().persist(entity);
    }

    public T edit(T entity)
    {
        return getEntityManager().merge(entity);
    }

    public void remove(T entity)
    {
        if (entity != null)
        {
            if (getEntityManager().contains(entity))
            {
                getEntityManager().remove(entity);
            }
            else
            {
                getEntityManager().remove(getEntityManager().merge(entity));
            }
        }
    }

    public T find(Object id)
    {
        return getEntityManager().find(entityClass, id);
    }

    public T getReference(Object id)
    {
        return getEntityManager().getReference(entityClass, id);
    }

    public List<T> findAll()
    {
        javax.persistence.criteria.CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range)
    {
        javax.persistence.criteria.CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        javax.persistence.TypedQuery<T> q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count()
    {
        javax.persistence.criteria.CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
