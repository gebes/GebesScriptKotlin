package eu.gebes

import eu.gebes.commands.Command
import eu.gebes.commands.CommandManager
import java.util.*
import eu.gebes.utils.*

class GebesScript(scriptFile: ScriptFile) {

    var commandManager: CommandManager = CommandManager()

    private var methods: LinkedList<ScriptMethod> = LinkedList()

    fun methodByName(name: String): ScriptMethod? =
        methods.stream().filter { t: ScriptMethod -> t.name == name }.findFirst().orElse(null);

    init {

        for (line in scriptFile.lines) {

            if (getIntend(line) == 0) {

                val name = stripIntend(line);

                if (methodByName(name) != null)
                    throw ParseException("Method with name $name already exists")

                methods.add(ScriptMethod(name, this))

            } else if (getIntend(line) == 1) {

                val commandName = stripIntend(line)

                val s = commandName.split(" ".toRegex(),  2)

                methods.last.addCommand(ScriptCommand(s[0], if (s.size == 2) s[1] else null))

            } else if (getIntend(line) == 2) {

                val argument = stripIntend(line)

                methods.last.lastCommand().addArgument(argument)

            }

        }

    }

    fun invokeMethod(name: String) {
        val method = methodByName(name)
            ?: throw ScriptRuntimeException("The method with the name $name does not exist")


        method.execute();
    }



}

class ScriptMethod(val name: String, val gebesScript: GebesScript) {


    private val commands: LinkedList<ScriptCommand> = LinkedList()

    fun execute() {
        commands.forEach { scriptCommand: ScriptCommand ->
            val command = gebesScript.commandManager.getCommandByName(scriptCommand.name)
                ?: throw ScriptRuntimeException("The command with the name ${scriptCommand.name} does not exist")

            command.execute(scriptCommand.name, scriptCommand.parameter,
                scriptCommand.arguments, gebesScript)

        }
    }

    fun addCommand(scriptCommand: ScriptCommand) = commands.add(scriptCommand)

    fun lastCommand(): ScriptCommand = commands.last

}

class ScriptCommand(val name: String, val parameter: String?) {

    val arguments: LinkedList<String> = LinkedList()


    fun addArgument(name: String) = arguments.add(name)

}

class ParseException(name: String) : Exception(name)
class ScriptRuntimeException(name: String) : Exception(name)


