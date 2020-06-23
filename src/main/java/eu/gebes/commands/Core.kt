package eu.gebes.commands

import eu.gebes.GebesScript
import eu.gebes.ScriptRuntimeException

/**
 * Prints stuff to the console with some extra animations/features
 */
class Printer(
    private val delay: Long = 0,
    private val wait_symbol: String? = null,
    private val wait_factor: Float = 1f
) {


    fun print(l: String) {
        var line = l
        if (wait_symbol != null)
            line = l.replace(wait_symbol.toRegex(), "\t");


        line.toCharArray().forEach { c: Char ->

            if (c == '\t') {
                Thread.sleep((delay * wait_factor).toLong())
            } else {
                print(c)
                Thread.sleep(delay)
            }
        }


    }


    fun println(line: String) {
        print(line + "\n")
    }


}

class printerCommand : Command() {
    override fun name(): String = "printer"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {

        if (parameter != null)
            throw ScriptRuntimeException("No parameter allowed at printer")


        var delay = 0f
        var wait_symbol: String? = null
        var wait_factor = 1f
        args.forEach { arg: String ->

            if (arg.startsWith("delay ")) {
                val param = arg.substring("delay ".length, arg.length)

                delay = param.toFloatOrNull()
                    ?: throw ScriptRuntimeException("Invalid float parameter for delay: $param");

            } else if (arg.startsWith("wait_symbol ")) {
                val param = arg.substring("wait_symbol ".length, arg.length)

                if (param.isEmpty())
                    throw ScriptRuntimeException("No parameter given at wait_symbol")

                wait_symbol = param

            } else if (arg.startsWith("wait_factor ")) {
                val param = arg.substring("wait_factor ".length, arg.length)

                wait_factor = param.toFloatOrNull()
                    ?: throw ScriptRuntimeException("Invalid float parameter for wait_factor: $param");

            } else {
                throw ScriptRuntimeException("Illegal argument for printer: $arg")
            }

        }

        gebesScript.printer = Printer((delay * 1000).toLong(), wait_symbol, wait_factor)

    }

}

class print : Command() {
    override fun name(): String = "print"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {

        if (parameter != null)
            if (args.isEmpty())
                gebesScript.printer.print(parameter)
            else
                gebesScript.printer.println(parameter)
        for ((i, arg) in args.withIndex())
            if (i != args.size - 1)
                gebesScript.printer.println(arg)
            else
                gebesScript.printer.print(arg)
    }

}

class println : Command() {
    override fun name(): String = "println"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {
        if (parameter != null)
            gebesScript.printer.println(parameter)
        for ((index, arg) in args.withIndex())
            gebesScript.printer.println(arg)

        if (parameter == null && args.isEmpty())
            println("")

    }

}

class wait : Command() {
    override fun name(): String = "wait"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {

        if (parameter == "for enter") {
            readLine()
        } else {
            val time = parameter?.toFloatOrNull()
                ?: throw ScriptRuntimeException("Invalid float parameter for wait: $parameter");

            Thread.sleep((time * 1000).toLong())
        }
    }

}

class clear : Command(){
    override fun name(): String = "clear"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }
}

