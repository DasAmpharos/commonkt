import groovy.lang.GroovyObject
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import org.jfrog.gradle.plugin.artifactory.dsl.ResolverConfig

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
    id("info.solidsoft.pitest") version "1.4.0"
    id("com.jfrog.artifactory") version "4.9.7"
}

allprojects {
    group = "io.github.dylmeadows"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
    }
}

subprojects {
    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("java-library")
    plugins.apply("maven")
    plugins.apply("maven-publish")
    plugins.apply("com.jfrog.artifactory")
    plugins.apply("info.solidsoft.pitest")

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.junit.jupiter:junit-jupiter:5.5.0")
        testImplementation("org.mockito:mockito-core:2.23.4")
    }

    val compileJava: JavaCompile by tasks
    val compileKotlin: KotlinCompile by tasks
    compileJava.destinationDir = compileKotlin.destinationDir

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    val sourcesJar by tasks.creating(Jar::class) {
        setProperty("archiveClassifier", "sources")
        from(sourceSets["main"].allSource)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    val javadocJar by tasks.creating(Jar::class) {
        setProperty("archiveClassifier", "javadoc")
        from("$buildDir/javadoc")
    }

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJavaLibrary") {
                from(components.getByName("java"))
                artifact(sourcesJar)
                artifact(javadocJar)
            }
        }
    }

    artifactory {
        setContextUrl("https://oss.jfrog.org/artifactory")
        publish(delegateClosureOf<PublisherConfig> {
            repository(delegateClosureOf<GroovyObject> {
                setProperty("repoKey", "oss-snapshot-local")
                setProperty("username", System.getenv("BINTRAY_USER"))
                setProperty("password", System.getenv("BINTRAY_KEY"))
                setProperty("maven", true)
            })
            defaults(delegateClosureOf<GroovyObject> {
                invokeMethod("publications", "mavenJava")
                invokeMethod("publishConfigs", arrayOf("archives", "published"))
                setProperty("publishArtifacts", true)
                setProperty("publishPom", true)
            })
        })
        resolve(delegateClosureOf<ResolverConfig> {
            repository(delegateClosureOf<GroovyObject> {
                setProperty("repoKey", "libs-release")
                setProperty("username", System.getenv("BINTRAY_USER"))
                setProperty("password", System.getenv("BINTRAY_KEY"))
                setProperty("maven", true)
            })
        })
    }
}
