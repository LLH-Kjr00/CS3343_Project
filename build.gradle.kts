import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

version = "1.0.0"

plugins {
    kotlin("jvm") version "1.8.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    // Dagger
    implementation("com.google.dagger:dagger:2.48")
    annotationProcessor("com.google.dagger:dagger-compiler:2.48")
}

application {
    mainClass.set("cs3343.animalchess.Main")
}

val sourcesJar by tasks.registering(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val shadowJar = (tasks["shadowJar"] as ShadowJar).apply {
    // relocate if needed
}

val deployPlugin by tasks.registering(Copy::class) {
    dependsOn(shadowJar)
    System.getenv("PLUGIN_DEPLOY_PATH")?.let {
        from(shadowJar)
        into(it)
    }
}

val build = (tasks["build"] as Task).apply {
    arrayOf(
        sourcesJar
        , shadowJar
        , deployPlugin
    ).forEach { dependsOn(it) }
}