package eu.gebes.commands

import eu.gebes.GebesScript

class print : Command() {
    override fun name(): String = "print"

    override fun execute(label: String, args: List<String>, gebesScript: GebesScript) {
        for (arg in args)
            print(arg)
    }

}

class println : Command() {
    override fun name(): String = "println"

    override fun execute(label: String, args: List<String>, gebesScript: GebesScript) {
        for (arg in args)
            println(arg)
    }

}

