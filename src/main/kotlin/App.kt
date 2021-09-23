import data.ExitCode.SUCCESS
import data.ExitCode.HELP
import data.ExitCode.INVALID_FILE
import services.ArgsParser
import services.getDiffText
import services.printHelp
import services.printNotExistFile
import java.io.File


class App {
    fun run(args: Array<String>): Int {
        val parser = ArgsParser()
        val parsedArgs = parser.parse(args)
        if (parsedArgs == null) {
            printHelp()
            return HELP.exitCode
        }
        if (!checkExistFiles(parsedArgs.file1, parsedArgs.file2)) {
            return INVALID_FILE.exitCode
        }
        val strings1 = getStringsFile(parsedArgs.file1)
        val strings2 = getStringsFile(parsedArgs.file2)
        println(getDiffText(strings1, strings2))
        return SUCCESS.exitCode
    }

    private fun checkExistFiles(file1: String, file2: String): Boolean {
        val valueExistFile1 = existFile(file1)
        val valueExistFile2 = existFile(file2)
        if (!valueExistFile1) {
            printNotExistFile(file1)
        }
        if (!valueExistFile2) {
            printNotExistFile(file2)
        }
        return valueExistFile1 and valueExistFile2
    }

    private fun existFile(file: String) = File(file).exists()

    private fun getStringsFile(file: String) = File(file).readText().split("\n")
}