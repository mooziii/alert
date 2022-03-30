plugins {
    java
    `maven-publish`
}

group = "me.obsilabor"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks {
    compileJava {
        options.release.set(11)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            this.groupId = project.group.toString()
            this.artifactId = project.name.toLowerCase()
            this.version = project.version.toString()
            from(components["java"])
        }
    }
}