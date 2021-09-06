package data

import java.io.File

data class Arguments(
    var file1: String? = null,
    var file2: String? = null
) {
    fun isEmpty() = file1 == null && file2 == null

    fun getNotExistFile(): String? = when {
        file1 == null || file2 == null -> null
        !File(file1!!).exists() -> file1
        !File(file2!!).exists() -> file2
        else -> null
    }
}
