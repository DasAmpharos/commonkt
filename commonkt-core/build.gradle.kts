description = "commonkt-core"
val moduleName by extra { "commonkt.core" }

//pitest {
//    targetClasses = ['io.github.dylmeadows.common.core.*']
//    threads = 4
//    outputFormats = ['XML', 'HTML']
//    mutationThreshold = 100
//}

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