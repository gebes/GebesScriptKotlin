package eu.gebes.utils

import eu.gebes.commands.CommandManager
import java.io.File

class CommandInfoCreator (val commandManager: CommandManager){

    fun generate() {
        Thread(this::_generate, "Async Info Generator").start();
    }

    fun _generate() {

        var content = "# GebesScript Command Information\n"

        for(command in commandManager.registeredCommands()){
            content += "## ${command.name()}\n";
            // TODO adding examples
        }

        val file = File("CommandInfo.md")

        file.delete()
        file.bufferedWriter().use { out ->
            out.write(content)
        }

    }


}