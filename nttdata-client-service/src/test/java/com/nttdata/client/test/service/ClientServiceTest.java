package com.nttdata.client.test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.nttdata.client.model.ClientEntity;
import com.nttdata.client.service.impl.ClientServiceImpl;
import com.nttdata.client.shared.constants.ClientConstants;

@SpringBootTest
public class ClientServiceTest {
	
	@InjectMocks
	private ClientServiceImpl clientService;
	
	public static final Integer INVALID_DOCUMENT = 1234567;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getClientReturnsCorrectClientTest() {
		
		String documentType = ClientConstants.DOCUMENT_TYPE_C;
		Integer document = ClientConstants.DOCUMENT;
		
		ClientEntity result = clientService.getClientByDocument(documentType, document);
		
		assertNotNull(result);
	}
	
	@Test
	public void getClientReturnNullTest() {
		String documentType = ClientConstants.DOCUMENT_TYPE_P;
		Integer document = INVALID_DOCUMENT;
		
		ClientEntity result = clientService.getClientByDocument(documentType, document);
		
		assertNull(result);
	}
	
	
}
