package eu.gebes

import eu.gebes.commands.*
import eu.gebes.script.GebesScript
import java.util.*


class CommandManager {

    private var commands: LinkedList<Command> = LinkedList()

    fun registerCommand(commandToAdd: Command) = commands.add(commandToAdd)
    fun registerCommands(commandsToAdd: Array<Command>) =
        commandsToAdd.forEach { commandToAdd -> registerCommand(commandToAdd) }

    fun registeredCommands() = commands

    fun getCommandByName(name: String): Command? =
        commands.stream().filter { t: Command -> t.name == name }.findFirst().orElse(null)

    init {
        registerCommands(
            arrayOf(
                printerCommand(), print(), println(),
                MethodCaller(), wait(), clear(),
                SetVariable(), ScanVariable(), If(),
                Note()
            )
        )
    }

}

abstract class Command {

    abstract val name: String
    abstract val description: String

    abstract fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String?

}