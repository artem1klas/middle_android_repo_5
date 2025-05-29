plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi())
}
allprojects {
    repositories {
        mavenCentral()
    }
}

gradlePlugin {
    plugins {
        create("find_untranslated_strings_plugin") {
            id = "find-untranslated-plugin"
            implementationClass =
                "com.example.buildsrc.FindUntranslatedStringsPlugin"
        }
    }
}
