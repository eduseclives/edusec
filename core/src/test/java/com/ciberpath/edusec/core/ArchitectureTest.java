package com.ciberpath.edusec.core

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.ciberpath.edusec.core")
class ArchitectureTest {

	@ArchTest
	static final ArchRule layer_dependencies_are_respected = Architectures.layeredArchitecture()
			.consideringAllDependencies().withOptionalLayers(true)
			.layer("Controllers").definedBy("com.ciberpath.edusec.core.web..")
			.layer("Services").definedBy("com.ciberpath.edusec.core.servicios..")
			.layer("Persistence").definedBy("com.ciberpath.edusec.core.repositorios..")
			.layer("Domain").definedBy("com.ciberpath.edusec.core.dominio..")
			.whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
			.whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
			.whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");

}