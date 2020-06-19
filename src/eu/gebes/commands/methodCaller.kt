package eu.gebes.commands

import eu.gebes.GebesScript

class methodCaller : Command() {
    override fun name(): String = "->"

    override fun execute(label: String, args: List<String>, gebesScript: GebesScript) {
        args.forEach { arg: String -> gebesScript.invokeMethod(arg) }
    }

}

class forever : Command() {
    override fun name(): String = "-> forever"

    override fun execute(label: String, args: List<String>, gebesScript: GebesScript) {
        while(true)
        args.forEach { arg: String -> gebesScript.invokeMethod(arg) }
    }

}