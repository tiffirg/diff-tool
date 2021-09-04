package data

enum class ExitCodes(val exitCode: Int) {
    SUCCESS(0),
    HELP(1),
    INVALID_FILE(2)
}