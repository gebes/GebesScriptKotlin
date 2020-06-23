package eu.gebes

import eu.gebes.script.GebesScript
import eu.gebes.script.ScriptFile
import eu.gebes.utils.FileSelector
import eu.gebes.utils.TitlePrinter
import java.io.File

fun main(args: Array<String>) {

   val titlePrinter = TitlePrinter()
   titlePrinter.print()

   val fileSelector = FileSelector(File("./scripts"))

   val gebesScript = GebesScript(ScriptFile(fileSelector.selectFile()))

   gebesScript.invokeMethod("main")

}