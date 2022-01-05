import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.openapi.generator") version "5.3.1"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "my.test.divisor"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.swagger:swagger-annotations:1.6.4")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.ninja-squad:springmockk:3.1.0")
}

val generatedSourcesDir = "$buildDir/generated/openapi"

openApiGenerate {
	generatorName.set("kotlin-spring")
	inputSpec.set("$rootDir/src/main/resources/openapi/DivisorAssignment.yaml")
	outputDir.set(generatedSourcesDir)
	apiPackage.set("my.test.divisor.kotlin.springboot.mvc.web.api")
	modelPackage.set("my.test.divisor.kotlin.springboot.mvc.web.model")
	configOptions.set(mapOf(
		"interfaceOnly" to "true",
		"dateLibrary" to "java8"
	))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

kotlin {
	sourceSets["main"].apply {
		kotlin.srcDir("$generatedSourcesDir/src/main/kotlin")
	}
}

tasks {
	val openApiGenerate by getting

	val compileKotlin by getting {
		dependsOn(openApiGenerate)
	}
}
