buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()

        flatDir {
            dirs("libs")
        }
    }
    dependencies {
        classpath(Classpath.gradle)
        classpath(Classpath.gradleKotlin)
        classpath(Classpath.navigationComponent)

        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
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