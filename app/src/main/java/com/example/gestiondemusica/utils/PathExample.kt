package com.example.gestiondemusica.utils

import java.nio.file.FileAlreadyExistsException
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.bufferedWriter
import kotlin.io.path.copyTo
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.moveTo
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.io.path.useLines
import kotlin.io.path.writeText

object PathExample {
    /**
     * List entries in a directory.
     */
    fun ls(path: String): List<Path> {
        return Path(path).listDirectoryEntries()
    }

    /**
     * Copy a file from source to destination.
     */
    fun cp(source: String, destination: String) {
        Path(source).copyTo(Path(destination))
    }

    /**
     * Move a file from source to destination.
     */
    fun mv(source: String, destination: String) {
        Path(source).moveTo(Path(destination))
    }

    /**
     * Move the file to a .deleted directory in the same
     * directory as the file. If the .deleted directory
     * does not exist it will be created.
     */
    fun softDelete(path: String) {
    }

    /**
     * Write `content` to the file in `path`.
     * If the file already exist it will override it.
     */
    fun writeTextAllAtOnce(path: String, content: String) {
        Path(path).writeText(content)
    }

    /**
     * Write `content` to the file in `path`.
     * @throws FileAlreadyExistsException if the file already exist.
     */
    fun writeTextAllAtOnceInNewFile(path: String, content: String) {
    }

    fun writeFileLineByLine(path: String) {
        Path(path).bufferedWriter().use { writer ->
            (1..10).forEach { i ->
                writer.appendLine("Line #$i")
            }
        }
    }

    fun readFileContent(path: String): String {
        return Path(path).readText()
    }

    fun readLines(path: String): List<String> {
        return Path(path).readLines()
    }

    fun countLines(path: String): Int {
        var lines = 0
        Path(path).useLines {
            it.forEach { _ -> // we don't need the line content
                lines++
            }
        }
        return lines
    }
}