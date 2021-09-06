package services

import kotlin.math.max


/**
 * Динамическая матрица для алгоритма LCS
 *
 * Объявление матрицы k + 1 на m + 1, где k и m - длины аргументов
 * Первые столбец и строка используются в качестве инициализации
 * Соотвественно 1..k и 1..m индексы предназначены для самой матрицы
 *
 * @see <a href="https://hsto.org/storage2/4c7/061/02a/4c706102aa8f467337723aa092f4bd5a.gif">Наглядная реализация</a>
 *
 */
private fun getDynamicMatrix(strings1: List<String>, strings2: List<String>): Array<Array<Int>> {
    val matrix = Array(strings1.size + 1) { Array(strings2.size + 1) { 0 } }
    for (i in strings1.indices) {
        for (j in strings2.indices) {
            if (strings1[i] == strings2[j])
                matrix[i + 1][j + 1] = matrix[i][j] + 1
            else
                matrix[i + 1][j + 1] = max(matrix[i][j + 1], matrix[i + 1][j])
        }
    }
    return matrix
}


/**
 * Реализация алгоритма LCS
 *
 */
fun applyLCS(strings1: List<String>, strings2: List<String>): List<String> {
    val matrix = getDynamicMatrix(strings1, strings2)
    val lcs = mutableListOf<String>()
    var i = strings1.size - 1
    var j = strings2.size - 1
    while (i >= 0 && j >= 0) {
        if (strings1[i] == strings2[j]) {
            lcs.add(strings1[i])
            --i; --j
        } else if (matrix[i][j + 1] > matrix[i + 1][j])
            --i
        else
            --j
    }
    return lcs.reversed()

}