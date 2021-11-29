buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
    }
}


allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
