plugins {
    id("java")
    id("maven-publish")
}

group = "me.devjakob.pixelclient"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
}

publishing  {
    repositories {
        maven {
            name = "github-packages"
            url = uri("https://maven.pkg.github.com/PixelClient/nbt")
            credentials {
                username = System.getenv("MAVEN_USER")
                password = System.getenv("MAVEN_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("ghPackages") {
            version = project.version.toString()
            from(components["java"])
        }
    }
}