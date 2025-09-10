package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ParcelCreateDto {

	
	private String sender;
	private String receiver;
	private String destination;
	private String content;
	private Status status;
	
	
	
	
	
}
