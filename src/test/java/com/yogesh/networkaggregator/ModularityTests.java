package com.yogesh.networkaggregator;


import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import static org.assertj.core.api.Assertions.assertThat;

class ModularityTests {
    final ApplicationModules modules = ApplicationModules.of(SocialMediaNetworkAggregatorApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        assertThat(modules).isNotNull();
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();
    }
}
