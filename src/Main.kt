import eu.gebes.GebesScript
import eu.gebes.ScriptFile
import java.io.File

fun main() {

   val gebesScript = GebesScript(ScriptFile(File("./scripts/loop.gebes")))

   gebesScript.invokeMethod("main")


}