package com.hcl.loginauth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginAuthorizationApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MvcConfig mvcConfig;
	
	@Autowired
	private WebSecurityConfig webSecurityConfig;
	
	@Test
	public void otherContextLoads() throws Exception {
		assertThat(webSecurityConfig).isNotNull();
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(mvcConfig).isNotNull();
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Welcome!");
	}

	@Test
	public void loginShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login", String.class))
				.contains("User Name");
	}

}
