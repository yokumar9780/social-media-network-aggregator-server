package com.yogesh.networkaggregator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test class for verifying the Spring application context
 * configuration.
 * This test ensures that the application context loads successfully with all
 * required
 * beans and dependencies, including the test containers configuration.
 */
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SocialMediaNetworkAggregatorApplicationTests {

	/**
	 * The Spring application context, automatically injected by Spring.
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Tests that the Spring application context loads successfully.
	 * Verifies that all required beans are properly configured and the context
	 * is initialized without errors.
	 */
	@Test
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}

}
