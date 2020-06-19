package eu.gebes.commands

import eu.gebes.GebesScript
import eu.gebes.ScriptRuntimeException

class methodCaller : Command() {
    override fun name(): String = "->"

    override fun execute(label: String, parameter: String?, args: List<String>, gebesScript: GebesScript) {

        var count: Int = 0;

        if (parameter == null || parameter == "once") {
            count = 1
        } else if (parameter == "forever") {
            count = -1
        } else {
            count = parameter.toIntOrNull()
                ?: throw ScriptRuntimeException("Invalid parameter for Method Caller: $parameter")
        }

        while(count != 0){
            args.forEach { arg: String -> gebesScript.invokeMethod(arg) }

            if(count >= 0)
                count--
        }

    }

}

