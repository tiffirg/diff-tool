import data.ExitCodes.*
import services.ArgsParser
import services.applyLCS
import services.printNotExistFile
import java.io.File


class App {
    fun run(args: Array<String>): Int {
        val parser = ArgsParser()
        val parsedArgs = parser.parse(args)
        if (parsedArgs.isEmpty())
            return HELP.exitCode
        val notExistFile = parsedArgs.getNotExistFile()
        if (notExistFile != null) {
            printNotExistFile(notExistFile)
            return INVALID_FILE.exitCode
        }
        val strings1 = getStringsFile(parsedArgs.file1!!)
        val strings2 = getStringsFile(parsedArgs.file2!!)
        println(compareTexts(strings1, strings2))
        return SUCCESS.exitCode
    }

    private fun getStringsFile(file: String) = File(file).readText().split("\n")

    private fun compareTexts(strings1: List<String>, strings2: List<String>): String {
       TODO()
    }
}