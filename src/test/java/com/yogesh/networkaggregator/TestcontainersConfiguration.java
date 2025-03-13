package com.yogesh.networkaggregator;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Test configuration class for setting up Testcontainers.
 * This configuration provides containerized dependencies (MongoDB) for
 * integration testing,
 * using Testcontainers to manage Docker containers during test execution.
 */
@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	/**
	 * Creates and configures a MongoDB container for testing.
	 * The container uses the latest MongoDB Docker image and is automatically
	 * managed by Spring Boot's service connection support.
	 *
	 * @return a configured MongoDB container instance
	 */
	@Bean
	@ServiceConnection
	MongoDBContainer mongoDbContainer() {
		return new MongoDBContainer(DockerImageName.parse("mongo:latest"));
	}

}
