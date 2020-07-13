package eu.gebes.commands

import eu.gebes.Command
import eu.gebes.script.GebesScript

class Note : Command() {
    override fun name(): String = "note"

    override fun description() = """
        Side notes which can be printed
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {
        return null
    }
}