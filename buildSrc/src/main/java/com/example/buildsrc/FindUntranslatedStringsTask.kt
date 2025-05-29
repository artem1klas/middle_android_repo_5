package com.example.buildsrc

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

abstract class FindUntranslatedStringsTask : DefaultTask() {

    private companion object {
        const val RESOURCES_DIRECTORY = "src/main/res"
        const val VALUES_FOLDER = "values"
        const val STRING_FILE = "strings.xml"
    }
    @TaskAction
    fun findUntranslatedStrings() {

        val resDir = File(project.projectDir, RESOURCES_DIRECTORY)
        val strings = File(resDir, "$VALUES_FOLDER/$STRING_FILE")
        val defaultStrings = getNamesFromFile(strings)

        val foldersWithValues = resDir.listFiles { file ->
            file.isDirectory
                    && file.name.startsWith("values-")
                    && (file.listFiles()?.any { it.isFile && it.name == STRING_FILE } == true)
        }

        val missingStrings = mutableMapOf<String, MutableList<String>>()

        var b = listOf<String>()

        foldersWithValues?.let { folders ->
            for (folder in folders) {
                val translatedStrings = getNamesFromFile(File(resDir, "${folder.name}/$STRING_FILE"))
                b = translatedStrings
                defaultStrings.forEach { string ->
                    if (!translatedStrings.contains(string)) {
                        missingStrings[folder.name] = missingStrings.getOrDefault(folder.name, mutableListOf()).apply { add(string) }
                    }
                }
            }
        }

//        if (missingStrings.isNotEmpty()) {
        if (true) {
            val stringBuilderErrorText = StringBuilder("Missing translations ${b}").append(System.lineSeparator())
            missingStrings.forEach { missing ->
                stringBuilderErrorText
                    .append("=== ${missing.key} ===")
                    .append(System.lineSeparator())
                    .append(missing.value.joinToString(separator = System.lineSeparator()))
                    .append(System.lineSeparator())

            }
            throw GradleException(stringBuilderErrorText.toString())
        }


    }

    fun getNamesFromFile(file: File): List<String> {
        val stringsFromXml = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(file)
            .getElementsByTagName("string")

        val stringIdentities =  stringsFromXml.let { nodeList ->
            (0 until nodeList.length).map { i ->
                val node = nodeList.item(i)
                val name = node.attributes?.getNamedItem("name")?.nodeValue ?: ""
                name
            }
        }
        return stringIdentities
    }
}