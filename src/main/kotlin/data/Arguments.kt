package data

data class Arguments(
    var file1: String? = null,
    var file2: String? = null
) {
    fun isEmpty() = file1 == null && file2 == null
}
