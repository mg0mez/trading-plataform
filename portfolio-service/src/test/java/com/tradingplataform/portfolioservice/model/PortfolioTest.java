package com.tradingplataform.portfolioservice.model;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { PortfolioTest.class })
class PortfolioTest {

	@Test @Order(1)
	void testPortfolioModel() {

		Portfolio p1 = new Portfolio(1, "product name", 1, 10);
		Portfolio p2 = new Portfolio();
		p2.setId(2);
		p2.setProductName("product name");
		p2.setUserId(1);
		p2.setCuantity(10);
		
		assertAll("Test getters and setters",
				()->assertNotEquals(p1.getId(), p2.getId()),
				()->assertEquals(p1.getProductName(), p2.getProductName()),
				()->assertEquals(p1.getUserId(), p2.getUserId()),
				()->assertEquals(p1.getCuantity(), p2.getCuantity())
				);
		
		assertEquals(p1.equals(p2), false);
		assertEquals("Portfolio(id=1, productName=product name, userId=1, cuantity=10)", p1.toString());
		assertEquals(968520375,p1.hashCode());
	}

}