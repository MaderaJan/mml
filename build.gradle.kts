buildscript {
    repositories {
        mavenCentral()
        google()

        flatDir {
            dirs("libs")
        }
    }
    dependencies {
        classpath(Classpath.gradle)
        classpath(Classpath.gradleKotlin)
        classpath(Classpath.navigationComponent)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}