package com.example.demo;

import com.example.demo.controllers.AppUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Hassan Ali
 * @since 23rd December 2024
 */
@SpringBootTest
class DemoApplicationTests {


	@Autowired
	private AppUserController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
