plugins {
    kotlin("jvm")
}

val coroutinesVersion: String by project

dependencies {
    implementation(kotlin("stdlib-common"))

    implementation(project(":discussions:discussions-common"))
    implementation(project(":discussions:discussions-stubs"))
    implementation(project(":discussions:discussions-lib-cor"))

    implementation(project(":discussions:discussions-repo-in-memory"))
    implementation(project(":discussions:discussions-repo-stubs"))
    implementation(project(":discussions:discussions-repo-tests"))

    implementation(kotlin("test-common"))
    implementation(kotlin("test-annotations-common"))

    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("test-junit"))

}