package com.yogesh.networkaggregator;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for verifying and documenting the application's modular structure.
 * This class uses Spring Modulith to analyze and validate the modular
 * architecture
 * of the application, as well as generate documentation for the modules.
 */
class ModularityTests {
    /**
     * Application modules instance representing the modular structure of the
     * application.
     */
    final ApplicationModules modules = ApplicationModules.of(SocialMediaNetworkAggregatorApplication.class);

    /**
     * Verifies that the application's modular structure adheres to defined rules.
     * This test ensures that module boundaries are respected and dependencies are
     * valid.
     */
    @Test
    void verifiesModularStructure() {
        modules.verify();
    }

    /**
     * Generates documentation for the application's modular structure.
     * Creates PlantUML diagrams for both the overall module structure and
     * individual modules,
     * along with detailed module documentation.
     */
    @Test
    void createModuleDocumentation() {
        assertThat(modules).isNotNull();
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();
    }
}
