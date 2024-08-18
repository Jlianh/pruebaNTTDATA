package com.nttdata.client.service.impl;

import org.springframework.stereotype.Service;

import com.nttdata.client.model.ClientEntity;
import com.nttdata.client.service.ClientService;
import com.nttdata.client.shared.constants.ClientConstants;

@Service
public class ClientServiceImpl implements ClientService {
	
	public ClientEntity getClientByDocument(String documentType, Integer document) {
		if(document.equals(ClientConstants.DOCUMENT) &&
				documentType.equals(ClientConstants.DOCUMENT_TYPE_C)) {
			ClientEntity client = new ClientEntity();
			client.setId(ClientConstants.ID);
			client.setDocument(ClientConstants.DOCUMENT);
			client.setTypeDocument(ClientConstants.DOCUMENT_TYPE_C);
			client.setFirstName(ClientConstants.FIRST_NAME);
			client.setSecondName(ClientConstants.SECOND_NAME);
			client.setFirstSurname(ClientConstants.FIRST_SURNAME);
			client.setSecondSurname(ClientConstants.SECOND_SURNAME);
			client.setPhone(ClientConstants.PHONE);
			client.setAddress(ClientConstants.ADDRESS);
			client.setCity(ClientConstants.CITY);
			return client;
		} else {
			return null;
		}
	}
	
}
