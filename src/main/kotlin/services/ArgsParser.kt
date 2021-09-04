package services

import data.Arguments

private const val AMOUNT_ARGS = 2

class ArgsParser {
    fun parse(args: Array<String>): Arguments {
        if (args.size != AMOUNT_ARGS)
            return Arguments()
        return Arguments(args[0], args[1])
    }
}