package eu.gebes

import java.io.File
import java.util.*

class GebesScript(private val scriptFile: ScriptFile) {



    init {

        for(line in scriptFile.lines){



        }

    }

    private fun getIntend(line: String): Int {

        var count = 0

        for (char in line.toCharArray())
            if(char == ' ')
                count++
            else return count

    }

}

class ScriptMethod(private val name: String){

    private val commands: List<ScriptCommand> = LinkedList()


}

class ScriptCommand(private val name: String){

    private val arguments: List<String> = LinkedList()



}

