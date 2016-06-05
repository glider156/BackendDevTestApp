package com.example.backenddev.service;

import java.util.List;

import com.example.backenddev.model.ActivationCode;
import com.example.backenddev.model.User;

public interface ActivationCodeService extends EntityService<ActivationCode> {
	ActivationCode generateNew();
	List<ActivationCode> getAll();
    ActivationCode findByCode(String code);
    ActivationCode findByUser(User user);
}
