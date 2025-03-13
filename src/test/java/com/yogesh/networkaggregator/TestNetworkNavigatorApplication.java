package com.yogesh.networkaggregator;

import org.springframework.boot.SpringApplication;

/**
 * Test application class for running the application with test configuration.
 * This class provides a main method that starts the application with
 * test-specific
 * configuration, including Testcontainers setup for integration testing.
 */
public class TestNetworkNavigatorApplication {

	/**
	 * Main method for running the application in test mode.
	 * Starts the Spring Boot application with test containers configuration,
	 * setting up necessary test dependencies like MongoDB containers.
	 *
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.from(SocialMediaNetworkAggregatorApplication::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}

}
