package com.example.backenddev.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.backenddev.model.Phone;

@Stateless
public class PhonesRepository extends DevTestAbstractRepository<Phone> {

	public PhonesRepository() {
		super(Phone.class);
	}

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
