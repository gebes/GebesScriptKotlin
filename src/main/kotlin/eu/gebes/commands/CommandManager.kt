package eu.gebes.commands

import eu.gebes.script.GebesScript
import java.util.*


class CommandManager {

    private var commands: LinkedList<Command> = LinkedList()

    fun registerCommand(command: Command) = commands.add(command)

    fun getCommandByName(name: String): Command? =
        commands.stream().filter { t: Command -> t.name() == name}.findFirst().orElse(null)

    init {
        registerCommand(printerCommand())
        registerCommand(print())
        registerCommand(println())
        registerCommand(MethodCaller())
        registerCommand(wait())
        registerCommand(clear())
        registerCommand(SetVariable())
        registerCommand(ScanVariable())
    }

}

abstract class Command {

    abstract fun name(): String

    abstract fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String?

}