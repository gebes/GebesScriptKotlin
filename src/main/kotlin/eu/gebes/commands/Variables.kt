package eu.gebes.commands

import eu.gebes.Command
import eu.gebes.script.GebesScript
import eu.gebes.script.ScriptRuntimeException

fun valueFromString(value: String?): Any? {

    return when {
        value == null -> {
            null
        }
        value.toIntOrNull() != null -> {
            value.toInt()
        }
        value.toFloatOrNull() != null -> {
            value.toFloat()
        }
        value.equals("true", ignoreCase = true) or value.equals("false", ignoreCase = true) -> {
            value.toBoolean()
        }
        else -> {
            value
        }
    }

}

fun validateVariableName(value: String) {
    if (value.contains(" "))
        throw ScriptRuntimeException("No spaces are allowed in variable nanmes")
}


class VariableManager {

    private var variables = HashMap<String, Any?>()

    fun setVariable(name: String, value: Any?) {
        variables[name] = value
    }

    fun getVariable(name: String): Any? =
        variables.get(name)

    fun getVariableString(name: String): String = getVariable(name).toString()

    fun deleteVariable(name: String) = variables.remove(name)


}

class SetVariable : Command() {
    override val name: String = "var"

    override val description = """
        Set the value of a variable
    """.trimIndent()

    /**
     * @param parameter is the name
     * @param args if there are more than one elements, then a mutable list will be created
     */
    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {

        parameter ?: return "A parameter as the name for the variable is required"

        validateVariableName(parameter)

        if (args.isEmpty()) return "A value is required in form of arguments"

        var value: Any?

        if (args.size == 1) {
            value = valueFromString(args.first())
        } else {
            value = mutableListOf<Any?>()
            args.forEach { arg: String ->
                value.add(valueFromString(arg))
            }
        }

        gebesScript.variableManager.setVariable(parameter, value)

        return null
    }


}

class ScanVariable : Command() {
    override val name: String = "scan"

    override val description = """
        Scan input from the console and set it to a specific variable
    """.trimIndent()

    /**
     * @param parameter is the name
     * @param args if there are more than one elements, then a mutable list will be created
     */
    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript): String? {


        if (parameter == null)
            return "Parameter is required"

        gebesScript.variableManager.setVariable(parameter, valueFromString(readLine()))

        return null
    }

}