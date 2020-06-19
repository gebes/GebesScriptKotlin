package eu.gebes.commands

import eu.gebes.GebesScript
import eu.gebes.ScriptRuntimeException
import java.util.*

class print : Command() {
    override fun name(): String = "print"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {
        if (parameter != null)
            if (args.size == 0)
                print(parameter)
            else
                println(parameter)
        for ((i, arg) in args.withIndex())
            if (i != args.size - 1)
                println(arg)
            else
                print(arg)
    }

}

class println : Command() {
    override fun name(): String = "println"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {
        println(parameter)
        for (arg in args)
            println(arg)
    }

}

class wait : Command() {
    override fun name(): String = "wait"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {

        val time = parameter?.toFloatOrNull()
            ?: throw ScriptRuntimeException("Invalid float parameter for wait: $parameter");

        Thread.sleep((time * 1000).toLong())

    }

}

