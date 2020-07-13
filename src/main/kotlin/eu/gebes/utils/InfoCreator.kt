package eu.gebes.utils

import eu.gebes.commands.CommandManager
import java.io.File

class InfoCreator(val commandManager: CommandManager) {

    fun generate() {
        Thread(this::_generate, "Async Info Generator").start()
    }

    fun _generate() {

        var content = """
            # ScriptInfo
            This Info was generated with the Version ${VersionManager.VERSION}
             
            ## About
            This file auto generates asynchronously everytime a script gets executed.  
            Here you will find a helpful overview of all commands you can use
            
            ## Commands
            
        """.trimIndent()

        for (command in commandManager.registeredCommands()) {
            content += "### ${command.name()}\n"
            // TODO adding examples
        }

        val file = File("ScriptInfo.md")

        file.delete()
        file.bufferedWriter().use { out ->
            out.write(content)
        }

    }


}