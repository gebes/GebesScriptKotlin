package eu.gebes.commands

import eu.gebes.GebesScript
import eu.gebes.ScriptRuntimeException
import kotlin.system.exitProcess

class print : Command() {
    override fun name(): String = "print"

    override fun execute(label: String,parameter: String?,  args: List<String>, gebesScript: GebesScript) {
        for (arg in args)
            print(arg)
    }

}

class println : Command() {
    override fun name(): String = "println"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {
        for (arg in args)
            println(arg)
    }

}

class wait : Command() {
    override fun name(): String = "wait"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {

        val time = parameter?.toFloatOrNull()
            ?: throw ScriptRuntimeException("Invalid float parameter for wait: $parameter");

        Thread.sleep((time*1000).toLong())

    }

}

