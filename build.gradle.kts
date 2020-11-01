buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
        mavenCentral()

        flatDir {
            dirs("libs")
        }
    }
    dependencies {
        classpath(Classpath.gradle)
        classpath(Classpath.gradleKotlin)

        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath(kotlin("serialization", version = Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}