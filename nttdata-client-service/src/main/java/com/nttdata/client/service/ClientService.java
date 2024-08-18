package com.nttdata.client.service;

import org.springframework.stereotype.Service;

import com.nttdata.client.model.ClientEntity;

@Service
public interface ClientService {

	public ClientEntity getClientByDocument(String documentType, Integer document);
}
