package eu.gebes

import eu.gebes.GebesScript
import eu.gebes.ScriptFile
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {

   val gebesScript = GebesScript(ScriptFile(File("./scripts/print.gebes")))


   gebesScript.invokeMethod("main")


}