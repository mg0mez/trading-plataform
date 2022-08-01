package com.tradingplataform.transactionservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
	
	private String recipient;
	private String subject;
	private String content;

}
