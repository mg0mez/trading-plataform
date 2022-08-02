package com.tradingplataform.portfolioservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio")
public class Portfolio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private int userId;
	private int cuantity;
}