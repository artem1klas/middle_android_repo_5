package com.example.buildsrc

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class FindUntranslatedStringsTask : DefaultTask() {
    @TaskAction
    fun findUntranslatedStrings() {

        val resDir = File(project.projectDir, "src/main/res")
        val strings = File(resDir, "values/strings.xml")


    }
}