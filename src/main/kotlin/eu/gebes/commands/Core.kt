package eu.gebes.commands

import eu.gebes.Command
import eu.gebes.script.GebesScript
import eu.gebes.script.ScriptRuntimeException
import java.util.*
import kotlin.collections.HashMap

/**
 * Prints stuff to the console with some extra animations/features
 */
class Printer(
    private val gebesScript: GebesScript,
    private val delay: Long = 0,
    private val wait_symbol: String? = null,
    private val wait_factor: Float = 1f
) {


    fun print(l: String) {
        var line = l.replace("%%", "\r")
        if (wait_symbol != null)
            line = l.replace(wait_symbol.toRegex(), "\t")


        val lineChars = line.toCharArray()
        var chars = LinkedList<Char>().toMutableList()

        var index = 0

        while (index < line.length) {

            val char = lineChars[index]

            if (char == '%') {
                if (index + 1 == line.length)
                    throw ScriptRuntimeException("Empty variable reference")

                var start = index + 1
                var i = start

                var current = lineChars[i++]
                var name = "$current"

                while (lineChars[i] != '%' && i != lineChars.size - 1) {
                    current = lineChars[i++]
                    name += current
                }

                if (i == lineChars.size - 1) {
                    index++
                    chars.add('%')
                    continue
                }

                if (name.endsWith("?")
                    && gebesScript.variableManager.getVariable(name.substring(0, name.length-1)) is List<*>) {
                    chars.addAll((gebesScript.variableManager.getVariable(name.substring(0, name.length-1)) as List<*>).random().toString().toCharArray().toTypedArray())
                } else
                    chars.addAll(gebesScript.variableManager.getVariableString(name).toCharArray().toTypedArray())
                index += i - start + 1
            } else {
                chars.add(if (char == '\r') '%' else char)
            }
            index++
        }


        for (c in chars) {
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

    override fun description() = "Modifies how and how fast something gets printed to the console"


    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {

        if (parameter != null)
            throw ScriptRuntimeException("No parameter allowed at printer")


        var delay = 0f
        var wait_symbol: String? = null
        var wait_factor = 1f
        args.forEach { arg: String ->

            if (arg.startsWith("delay ")) {
                val param = arg.substring("delay ".length, arg.length)

                delay = param.toFloatOrNull()
                    ?: return ("Invalid float parameter for delay: $param")

            } else if (arg.startsWith("wait_symbol ")) {
                val param = arg.substring("wait_symbol ".length, arg.length)

                if (param.isEmpty())
                    return ("No parameter given at wait_symbol")

                wait_symbol = param

            } else if (arg.startsWith("wait_factor ")) {
                val param = arg.substring("wait_factor ".length, arg.length)

                wait_factor = param.toFloatOrNull()
                    ?: return ("Invalid float parameter for wait_factor: $param")

            } else {
                return ("Illegal argument for printer: $arg")
            }

        }

        gebesScript.printer = Printer(gebesScript, (delay * 1000).toLong(), wait_symbol, wait_factor)

        return null
    }

}

class print : Command() {
    override fun name(): String = "print"

    override fun description() = """
        Prints the parameter and arguments to the console as you parsed them
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {

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

        return null
    }

}

class println : Command() {
    override fun name(): String = "println"

    override fun description() = """
        Prints the parameter and arguments to the console with an linebreak at the end
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {
        if (parameter != null)
            gebesScript.printer.println(parameter)
        for ((index, arg) in args.withIndex())
            gebesScript.printer.println(arg)

        if (parameter == null && args.isEmpty())
            println("")

        return null

    }

}

class wait : Command() {
    override fun name(): String = "wait"

    override fun description() = """
        Waits for n seconds
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {

        if (parameter == "for enter") {
            readLine()
        } else {
            val time = parameter?.toFloatOrNull()
                ?: return ("Invalid float parameter for wait: $parameter")

            Thread.sleep((time * 1000).toLong())
        }

        return null
    }

}

class clear : Command() {
    override fun name(): String = "clear"

    override fun description() = """
        Clears the console screen
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {
        print("\u001b[H\u001b[2J")
        System.out.flush()
        return null
    }
}

class If : Command() {
    override fun name(): String = "if"

    override fun description() = """
        Compares values and calls methods if true
    """.trimIndent()

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {

        parameter ?: return "Parameter is required"

        val value = gebesScript.variableManager.getVariable(parameter)

        val statements = HashMap<String, LinkedList<String>>()

        var last: String? = null

        // map the statements
        for (i in args.indices) {
            if (args[i].endsWith(":") && (args[i].startsWith("equals ")) || (args[i] == "else:")) {
                last = args[i]
                statements.put(last, LinkedList())
            } else {
                if (last == null)
                    continue

                val list = statements[last]

                list!!.add(if (args[i].startsWith(" ")) args[i].substring(1, args[i].length) else args[i])

                statements.put(last, list)

            }
        }

        var hasMatch = false

        for (entry in statements.entries) {

            if (entry.key.startsWith("equals ")) {


                val valueToCompare = entry.key.substring("equals ".length, entry.key.length - 1)

                if (value.toString() == valueToCompare) {
                    hasMatch = true
                    entry.value.forEach { method: String ->
                        gebesScript.invokeMethod(method)
                    }
                }

            }

        }

        if (!hasMatch) {
            statements["else:"]?.forEach { method: String ->
                gebesScript.invokeMethod(method)
            }
        }

        return null
    }
}

