package eu.gebes.utils

import java.io.File
import java.nio.file.Files
import java.util.*
import kotlin.streams.toList

/**
 * Prints the cool GebesScript title
 */
class TitlePrinter {

    fun print() {
        println()
        println(Title.title)
        println(Title.subtitle)
        println()
    }

}

object Title {
    // http://patorjk.com/software/taag/#p=display&f=Big&t=Type%20Something%20
    // Big
    val title: String = """
   _____      _                _____           _       _  
  / ____|    | |              / ____|         (_)     | |  
 | |  __  ___| |__   ___  ___| (___   ___ _ __ _ _ __ | |_ 
 | | |_ |/ _ \ '_ \ / _ \/ __|\___ \ / __| '__| | '_ \| __|
 | |__| |  __/ |_) |  __/\__ \____) | (__| |  | | |_) | |_
  \_____|\___|_.__/ \___||___/_____/ \___|_|  |_| .__/ \__|
                                                | |                         
                                                |_|                         """.trimIndent()

    val subtitle: String = """
        Running version ${VersionManager.VERSION}
        This is a stable release
        Syntax may change!
    """
}


/**
 * Selects a .gebes file from a folder
 */
class FileSelector(private var folder: File) {

    fun selectFile(): File {
        println("Select a .gebes file to execute")

        return makeSelection(folder)


    }

    private fun makeSelection(folder: File): File {
        val files = Arrays.stream(folder.listFiles()).filter { file: File -> file.name.endsWith(".gebes") || file.isDirectory }.toList().toMutableList()

        files.add(0, folder)
        if (folder.parentFile != null)
            files.add(1, folder.parentFile)

        println()
        println("Contents of " + folder.path)
        for ((i, file) in files.sortedByDescending { file -> file.isDirectory }.withIndex()) {
            var nameToPrint: String
            if (file.absolutePath == folder.absolutePath)
                nameToPrint = "📁."
            else if (folder.parent != null && file.absolutePath == folder.parent)
                nameToPrint = "📁.."
            else
                nameToPrint = if (file.isDirectory) "📁${file.name}" else "📄" + file.name

            println(" ${String.format("%${files.size.toString().length}d", i)}) " + nameToPrint)
        }

        var selection: Int
        do {
            print("Select a option: ")
            selection = readLine()!!.toInt()
        } while (!(selection >= 0 && selection < files.size))

        return if (files[selection].isDirectory)
            makeSelection(files[selection])
        else files[selection]
    }
    /**
     *
        private fun hasScriptFile(folder: File?): Boolean {
        if (folder != null && folder.isDirectory && folder.listFiles() != null)
        for (file in folder.listFiles()!!) {

        if (file.isDirectory) {
        if (hasScriptFile(file))
        return true
        } else if (file.name.endsWith(".gebes"))
        return true

        }
        return false
        }
     */

    /**
     *       if (folder.mkdir())
    println("Note: There was no ./scripts folder, so I created one.")

    val files = Arrays.stream(folder.listFiles()).filter { file: File -> file.name.endsWith(".gebes") }.toList()
    while (files.isEmpty()) {
    println("The folder ${folder.name} has no .gebes files. Press enter to refresh")
    readLine()
    }


    println("Actions for ./scripts")
    for ((index, file: File) in files.withIndex()) {
    println(" ${index + 1}) ${file.name}")
    }
    println()

    var selection: Int

    do {
    println("Select a file: ")
    selection = readLine()!!.toInt()
    } while (!(selection >= 0 && selection <= files.size))

    if (selection != 0)
    return files[selection - 1]

     */
}