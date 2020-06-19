import eu.gebes.GebesScript
import eu.gebes.ScriptFile
import java.io.File
import kotlin.system.exitProcess

fun main() {

   val gebesScript = GebesScript(ScriptFile(File("./scripts/book.gebes")))


   gebesScript.invokeMethod("main")


}