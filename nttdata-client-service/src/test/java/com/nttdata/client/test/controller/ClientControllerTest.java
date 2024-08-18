package com.nttdata.client.test.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.nttdata.client.controller.ClientController;
import com.nttdata.client.model.ClientEntity;
import com.nttdata.client.service.ClientService;
import com.nttdata.client.shared.constants.ClientConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ClientController clientController;

	@Mock
	private ClientService clientService;
	
	public static final Integer DOCUMENT_TEST = 12345;
	public static final Integer INVALID_DOCUMENT = -1;
	
	public static final String PARAM_DOCUMENT = "document";
	public static final String PARAM_DOCUMENT_TYPE = "documentType";

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void clientControllerBadRequestTest() throws Exception {
		mockMvc.perform(get("/api/v1/client/getClientByDocument").param(PARAM_DOCUMENT_TYPE, ClientConstants.DOCUMENT_TYPE_C)
				.param(PARAM_DOCUMENT, INVALID_DOCUMENT.toString())).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid information provided."));
	}

	@Test
	public void clientControllerNotFoundTest() throws Exception {
		when(clientService.getClientByDocument(anyString(), anyInt())).thenReturn(null);

		mockMvc.perform(get("/api/v1/client/getClientByDocument").param(PARAM_DOCUMENT_TYPE, ClientConstants.DOCUMENT_TYPE_C)
				.param(PARAM_DOCUMENT, DOCUMENT_TEST.toString())).andExpect(status().isNotFound())
				.andExpect(content().string("Client not found."));
	}

	@Test
	public void clientControllerFoundTest() throws Exception {
		ClientEntity client = new ClientEntity();
		client.setDocument(ClientConstants.DOCUMENT);
		client.setTypeDocument(ClientConstants.DOCUMENT_TYPE_C);

		when(clientService.getClientByDocument(ClientConstants.DOCUMENT_TYPE_C, ClientConstants.DOCUMENT))
				.thenReturn(client);

		mockMvc.perform(get("/api/v1/client/getClientByDocument").param(PARAM_DOCUMENT_TYPE, ClientConstants.DOCUMENT_TYPE_C)
				.param(PARAM_DOCUMENT, ClientConstants.DOCUMENT.toString())).andExpect(status().isOk())
				.andExpect(content().json("{'document':23445322,'typeDocument':'CC'}"));
	}

	@Test
	public void clientControllerServerErrorTest() throws Exception {
		when(clientService.getClientByDocument(anyString(), anyInt())).thenThrow(new RuntimeException("Unexpected Error"));

		mockMvc.perform(get("/api/v1/client/getClientByDocument").param(PARAM_DOCUMENT_TYPE, ClientConstants.DOCUMENT_TYPE_C)
				.param(PARAM_DOCUMENT, DOCUMENT_TEST.toString())).andExpect(status().isNotFound())
				.andExpect(content().string("Client not found."));
	}
}
