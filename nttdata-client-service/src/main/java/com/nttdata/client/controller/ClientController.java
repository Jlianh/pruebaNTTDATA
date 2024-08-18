package com.nttdata.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.nttdata.client.model.ClientEntity;
import com.nttdata.client.service.ClientService;
import com.nttdata.client.shared.constants.ClientConstants;

@RestController
@RequestMapping(value = "api/v1/client")
@CrossOrigin("*")
@Slf4j
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value = "/getClientByDocument")
    public ResponseEntity<?> getClientByDocument(
    		@RequestParam(value = "documentType", required = true) String documentType,
    		@RequestParam(value = "document", required = true) Integer document) {
        log.info("Fetching client with documentType: {} \n and document : {}", documentType, document);
        
        if ((documentType.equals(ClientConstants.DOCUMENT_TYPE_C) ||
        		documentType.equals(ClientConstants.DOCUMENT_TYPE_P))
        		&& (document.equals(null) || document <= 0)) {
            log.warn("Invalid document provided: {}", document);
            return ResponseEntity.badRequest().body("Invalid information provided.");
        }
        
        try {
            ClientEntity client = clientService.getClientByDocument(documentType, document);
            if (client == null) {
                log.info("No client found with document: {}", document);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
            }
            log.info("Client found with documentType: {} \n and document : {}", documentType, document);
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            log.error("Internal server error while fetching client with document: {}", document);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
