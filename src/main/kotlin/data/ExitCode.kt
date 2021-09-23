package data

enum class ExitCode(val exitCode: Int) {
    SUCCESS(0),
    HELP(1),
    INVALID_FILE(2)
}