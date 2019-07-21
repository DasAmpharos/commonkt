plugins {
    base
    id("org.openjfx.javafxplugin") version "0.0.7"
}

description = "commonkt-javafx"
val moduleName by extra { "commonkt.javafx" }

javafx {
    version = "11.0.2"
    modules = listOf(
        "javafx.controls"
    )
}

dependencies {
    implementation(project(":commonkt-core"))
}

tasks.withType<JavaCompile> {
    dependsOn(":compileKotlin")
    if (JavaVersion.current() >= JavaVersion.VERSION_1_9) {
        inputs.property("moduleName", ext["moduleName"])
        doFirst {
            val compileJava: JavaCompile by tasks
            compileJava.options.compilerArgs = listOf(
                "--module-path", sourceSets["main"].compileClasspath.asPath
            )
            sourceSets["main"].compileClasspath = files()
        }
    }
}