package com.itau.itauLocate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.itau.itauLocate.controller.AgencyController;
import com.itau.itauLocate.controller.LocationController;

@RunWith(SpringRunner.class)
@ComponentScan("com.itau.itauLocate")
@EntityScan("com.itau.itauLocate.model")
@EnableJpaRepositories("com.itau.itauLocate.dao")
@TestPropertySource(locations="classpath:test.properties")
@SpringBootTest(classes = {LocationController.class, AgencyController.class })
public class ItauLocateApplicationTests {

	@Test
	public void contextLoads() {
	}

}
