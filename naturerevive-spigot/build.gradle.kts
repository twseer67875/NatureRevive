/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.4/samples
 */


plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "engineer.skyouo.plugins.naturerevive.spigot"
version = project.rootProject.version

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://maven.playpro.com")
    maven("https://jitpack.io")
    maven("https://repo.glaremasters.me/repository/bloodshot")
    mavenCentral()
}

dependencies {
    implementation("me.carleslc.Simple-YAML:Simple-Yaml:1.8.2")

    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")

    compileOnly("com.zaxxer:HikariCP:4.0.3")

    compileOnly("it.unimi.dsi:fastutil:8.5.9")

    compileOnly("net.coreprotect:coreprotect:21.0")
    compileOnly("com.github.TechFortress:GriefPrevention:16.18")
    compileOnly("com.griefdefender:api:2.1.0-SNAPSHOT")
    compileOnly(files("libs/Residence5.1.1.1.jar"))

    implementation(project(":naturerevive-common"))
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("shaded")

        dependsOn(project.project(":naturerevive-spigot:nms").subprojects.map { it.tasks.named("assemble") })
        from(project(":naturerevive-spigot:nms").subprojects.map {
            zipTree(it.buildDir.resolve("libs/" + it.name + "-" + it.version + ".jar")).matching {
                exclude("META-INF")
            }
        })
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(11)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }
}