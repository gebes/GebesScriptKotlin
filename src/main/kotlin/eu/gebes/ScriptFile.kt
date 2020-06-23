package eu.gebes

import java.io.File
import java.util.*
import eu.gebes.utils.*
import kotlin.streams.toList

class ScriptFile {

    var lines: List<String>

    constructor(file: File) {
        this.lines = getLines(file);
    }

    constructor(lines: List<String>) {
        this.lines = stripComments(lines);
    }

}


/**
 * Returns all the lines of a file with the comments stripped of
 */
fun getLines(file: File): List<String> {
    return stripComments(file.readLines())
}

fun stripComments(lines: List<String>): List<String> {

    val newLines = LinkedList<String>()

    for (l in lines) {

        val line = stripIntend(l)

        if (line.startsWith("//") or line.startsWith("#") or (line.isEmpty() && l.length < 2))
            continue;

        newLines.add(l);

    }
    return newLines;
}


