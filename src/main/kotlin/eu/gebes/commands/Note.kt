package eu.gebes.commands

import eu.gebes.Command
import eu.gebes.script.GebesScript

class Note : Command() {
    override fun name(): String = "note"

    override fun description() = """
        Side notes which can be printed
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {

        if (gebesScript.variableManager.getVariable("gs.note.print") == true) {
            if (parameter != null) {
                gebesScript.printer.println(parameter)
            }
            args.forEach { line: String ->
                gebesScript.printer.println(line)
            }
        }

        return null
    }
}