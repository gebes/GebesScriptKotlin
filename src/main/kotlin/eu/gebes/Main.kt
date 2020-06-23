package eu.gebes

import eu.gebes.GebesScript
import eu.gebes.ScriptFile
import eu.gebes.utils.FileSelector
import eu.gebes.utils.TitlePrinter
import java.io.File
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {

   val titlePrinter = TitlePrinter()
   titlePrinter.print()


   val fileSelector = FileSelector(File("./scripts"))

   val gebesScript = GebesScript(ScriptFile(fileSelector.selectFile()))

   gebesScript.invokeMethod("main")

}