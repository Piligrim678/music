import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "ru.music"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotestVersion: String by project
val coroutinesVersion: String by project
val jUnitJupiterVersion: String by project

dependencies {
    implementation(kotlin("stdlib-common"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    implementation(kotlin("test-junit5"))
    implementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    implementation("org.junit.jupiter:junit-jupiter-params:$jUnitJupiterVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}