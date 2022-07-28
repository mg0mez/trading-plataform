package com.tradingplataform.authservice.service.impl;


import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.models.dto.LoginDto;
import com.tradingplataform.authservice.models.dto.RegisterDto;
import com.tradingplataform.authservice.models.dto.ResponseTokenDto;
import com.tradingplataform.authservice.security.JwtProvider;


@Service
@Transactional
public class AuthUserService{
	
	Logger log = Logger.getLogger(AuthUserService.class);
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * Método para registar un usuario en bd
	 * 
	 * @param RegisterDTO registerUserDto, objeto proviniente de la peticion del registro
	 * @return
	 */
	public User register(RegisterDto registerUserDto) {
		
		Optional<User> opUser = userService.findByEmail(registerUserDto.getEmail());
		if(opUser.isPresent()) {
			return null;
		}
		
		User user = new User(registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword()));
		
		return userService.save(user);
		
	}
	
	public ResponseTokenDto login(LoginDto loginUserDto) {
		Optional<User> opUser = userService.findByEmail(loginUserDto.getEmail());
		if(opUser.isEmpty()) {
			return null;
		}
		
		try {
			
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.createToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return new ResponseTokenDto(token,userDetails.getUsername());
		
		}catch (Exception e) {
			
			return null;
		}
				
	}
	
	public ResponseTokenDto validateToken(String token) {
		if(!jwtProvider.isValidateToken(token)) {
			return null;
		}
		
		String userName = jwtProvider.getEmailFromToken(token);
		
		if(userService.findByEmail(userName).isEmpty()) {
			return null;
		}
		
		return new ResponseTokenDto(token, userName);
		
	}






}