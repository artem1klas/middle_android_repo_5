package com.example.buildsrc

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class FindUntranslatedStringsTask : DefaultTask() {
    @TaskAction
    fun findUntranslatedStrings() {

    }
}