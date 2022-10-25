package com.mercedesbenz.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercedesbenz.shortening.ShorteningServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ShorteningServiceApplication.class })
class ShorteningServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
