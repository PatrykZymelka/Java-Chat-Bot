plugins {
    id("java")
    id("java-library")
    id("io.ktor.plugin") version "2.3.0"
    application
}

application {
    mainClass.set("org.Server.Main")
}
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}