/* CIBERPATH 2023 */
package com.ciberpath.edusec.services;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.ciberpath.edusec")
class ArchitectureTest {

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = Architectures.layeredArchitecture()
            .consideringAllDependencies().withOptionalLayers(true).layer("Controllers")
            .definedBy("com.ciberpath.edusec.services.web..").layer("Services")
            .definedBy("com.ciberpath.edusec.services.servicios..").layer("Persistence")
            .definedBy("com.ciberpath.edusec.services.repositorios..").layer("Domain")
            .definedBy("com.ciberpath.edusec.services.dominio..").whereLayer("Controllers")
            .mayNotBeAccessedByAnyLayer().whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");

}