package com.nttdata.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
	
	private Long id;
	private Integer document;
	private String typeDocument;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String phone;
	private String address;
	private String city;
	
}
