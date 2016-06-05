package com.example.backenddev.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.backenddev.data.ActivationCodeRepository;
import com.example.backenddev.model.ActivationCode;
import com.example.backenddev.model.User;

@Stateless
public class ActivationCodeServiceImpl implements ActivationCodeService {

	@Inject
	private SecureRandom secureRandom;
	
	@Inject
    private ActivationCodeRepository activationCodeRepository;
	
	@Override
	public void create(ActivationCode entity) {
		activationCodeRepository.create(entity);

	}

	@Override
	public ActivationCode getReference(Object id) {
		return activationCodeRepository.getReference(id);
	}

	@Override
	public ActivationCode find(Object id) {
		return activationCodeRepository.find(id);
	}

	@Override
	public ActivationCode update(ActivationCode entity) {
		return activationCodeRepository.edit(entity);
	}

	@Override
	public void delete(ActivationCode entity) {
		activationCodeRepository.remove(entity);
	}

	@Override
	public int count() {
		return activationCodeRepository.count();
	}

	private String generateCode() {
		return new BigInteger(130, secureRandom).toString(32);
	}
	
	@Override
	public ActivationCode generateNew() {
		ActivationCode code = new ActivationCode();
		code.setCode(generateCode());
		return code;
	}

	public List<ActivationCode> getAll()
	{
		return activationCodeRepository.findAll();
	}

	@Override
	public ActivationCode findByCode(String code) {
		return activationCodeRepository.findByCode(code);
	}

	@Override
	public ActivationCode findByUser(User user) {
		return activationCodeRepository.findByUser(user);
	}
	
}
