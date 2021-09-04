package services

fun printHelp() = println("Usage: diff <file1> <file2>")

fun printNotExistFile(nameFile: String) = println("diff: $nameFile: No such file or directory")