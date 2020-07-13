package eu.gebes.script

import java.io.File
import java.util.*

class ScriptFile {

    var lines: List<String>

    constructor(file: File) : this(file.readLines());

    constructor(lines: List<String>) {
        this.lines = CommentStripper.strip(lines)
    }

}




object CommentStripper{
    fun strip(lines: List<String>): List<String> {

        val newLines = LinkedList<String>()

        for (line in lines) {

            if (line.startsWith("//") or line.startsWith("#") or line.isEmpty())
                continue

            newLines.add(line)

        }
        return newLines
    }
}


