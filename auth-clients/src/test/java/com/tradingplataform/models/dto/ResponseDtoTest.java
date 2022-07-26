package com.tradingplataform.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResponseDtoTest {

	ResponseDto responseDto;

	@BeforeEach
	void setUp() throws Exception {
		responseDto = new ResponseDto();
	}

	@Test
	void testResponseDto() {
		ResponseDto responseDto = new ResponseDto();
		assertAll("ResponseDto()", () -> assertEquals(null, responseDto.getToken()),
				() -> assertEquals("Bearer", responseDto.getBearer()));
	}

	@Test
	void testResponseDtoStringString() {
		ResponseDto responseDto = new ResponseDto("token", "userName");

		assertAll("ResponseDto()", () -> assertEquals("token", responseDto.getToken()),
				() -> assertEquals("Bearer", responseDto.getBearer()),
				() -> assertEquals("userName", responseDto.getUserName()));
	}

	@Test
	void testGetToken() {
		responseDto.setToken("token2");
		responseDto.setUserName("username2");
		responseDto.setBearer("Bearer2");

		assertAll("ResponseDto()", () -> assertEquals("token2", responseDto.getToken()),
				() -> assertEquals("Bearer2", responseDto.getBearer()),
				() -> assertEquals("username2", responseDto.getUserName()));
	}

}
