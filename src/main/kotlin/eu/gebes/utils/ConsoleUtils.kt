package eu.gebes.utils

import java.io.File
import java.util.*
import kotlin.streams.toList

/**
 * Prints the cool GebesScript title
 */
class TitlePrinter {

    fun print() {
        println()
        println(title())
        println(subtitle())
        println()
    }

    // http://patorjk.com/software/taag/#p=display&f=Big&t=Type%20Something%20
    // Big
    private fun title(): String = """
   _____      _                _____           _       _  
  / ____|    | |              / ____|         (_)     | |  
 | |  __  ___| |__   ___  ___| (___   ___ _ __ _ _ __ | |_ 
 | | |_ |/ _ \ '_ \ / _ \/ __|\___ \ / __| '__| | '_ \| __|
 | |__| |  __/ |_) |  __/\__ \____) | (__| |  | | |_) | |_
  \_____|\___|_.__/ \___||___/_____/ \___|_|  |_| .__/ \__|
                                                | |                         
                                                |_|                         """.trimIndent()

    private fun subtitle(): String = "   ${VersionManager.VERSION}"


}


/**
 * Selects a .gebes file from a folder
 */
class FileSelector(private var folder: File) {

    fun selectFile(): File {

        while (true) {

            if (folder.mkdir())
                println("Note: There was no ./scripts folder, so I created one.")

            val files = Arrays.stream(folder.listFiles()).filter { file: File -> file.name.endsWith(".gebes") }.toList()
            while (files.isEmpty()) {
                println("The folder ${folder.name} has no .gebes files. Press enter to refresh")
                readLine()
            }


            println("Content of ./scripts")
            println(" 0) Refresh this list")
            for ((index, file: File) in files.withIndex()) {
                println(" ${index + 1}) ${file.name}")
            }
            println()

            var selection = 0

            do {
                println("Select a file: ")
                selection = readLine()!!.toInt()
            } while (!(selection >= 0 && selection <= files.size))

            if (selection != 0)
                return files.get(selection - 1)


        }


    }


}