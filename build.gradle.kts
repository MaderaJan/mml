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

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
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