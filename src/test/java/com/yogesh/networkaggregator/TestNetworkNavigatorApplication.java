package com.yogesh.networkaggregator;

import org.springframework.boot.SpringApplication;

public class TestNetworkNavigatorApplication {

	public static void main(String[] args) {
		SpringApplication.from(SocialMediaNetworkAggregatorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
