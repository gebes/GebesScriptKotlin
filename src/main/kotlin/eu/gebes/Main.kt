package eu.gebes

import eu.gebes.script.GebesScript
import eu.gebes.script.ScriptFile
import eu.gebes.utils.InfoCreator
import eu.gebes.utils.FileSelector
import eu.gebes.utils.TitlePrinter
import java.io.File

/**
 * @param args
 *  Possible arguments are:
 *  A file path to a File (which will be executed)
 *  --no-prefix doesnt print the file path before executing it
 */
fun main(args: Array<String>) {


    if (args.isEmpty()) {
        val titlePrinter = TitlePrinter()
        titlePrinter.print()

        val fileSelector = FileSelector(File("./scripts"))

        executeScript(fileSelector.selectFile())

    } else {


        for (arg in args) {


            if (arg.startsWith("--"))
                continue

            if (!args.contains(Parameter.NO_PREFIX.stringValue))
                println("Executing script $arg")


            executeScript(File(arg))

        }

    }

}

private enum class Parameter(val stringValue: String) {
    NO_PREFIX("--no-prefix");
}


private fun executeScript(file: File) {
    val gebesScript: GebesScript = GebesScript(ScriptFile(file))
    InfoCreator(gebesScript.commandManager).generate()
    gebesScript.invokeMethod("main")
}