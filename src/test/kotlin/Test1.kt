import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

val app = App()


internal class Test1 {

    private val standardOut = System.out
    private val stream = ByteArrayOutputStream()

    @BeforeTest
    fun setUp() {
        System.setOut(PrintStream(stream))
    }

    @AfterTest
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun testOfFiles() {
        val primaryPathDirectory = "src/test/resources/test"
        for (i in 1..9) {
            val pathDirectory = "$primaryPathDirectory$i/"
            val pathFileOrigin = "${pathDirectory}original.txt"
            val pathFileNew = "${pathDirectory}new.txt"
            val fileDiff = File("${pathDirectory}diff.txt")
            val textFileDiff = fileDiff.readText()

            val args = arrayOf(pathFileOrigin, pathFileNew)

            app.run(args)
            assertEquals(textFileDiff, stream.toString().trim())
            stream.reset()
        }
    }

    @Test
    fun testOfHelp1() {
        val args = arrayOf("src/test/data/test1/original.txt")
        app.run(args)
        assertEquals("Usage: diff <file1> <file2>", stream.toString().trim())
    }

    @Test
    fun testOfHelp2() {
        val args = emptyArray<String>()
        app.run(args)
        assertEquals("Usage: diff <file1> <file2>", stream.toString().trim())
    }

    @Test
    fun testOfExistFile1() {
        val file1 = "src/test/resources/test/original.txt"
        val file2 = "src/test/resources/test/new.txt"
        val args = arrayOf(file1, file2)
        app.run(args)
        assertEquals(
            "diff: $file1: No such file or directory\ndiff: $file2: No such file or directory",
            stream.toString().trim()
        )
    }

    @Test
    fun testOfExistFile2() {
        val file1 = "src/test/resources/test/original.txt"
        val file2 = "src/test/resources/test1/new.txt"
        val args = arrayOf(
            file1,
            file2
        )
        app.run(args)
        assertEquals("diff: $file1: No such file or directory", stream.toString().trim())
    }

    @Test
    fun testOfExistFile3() {
        val file1 = "src/test/resources/test1/original.txt"
        val file2 = "src/test/resources/test/new.txt"
        val args = arrayOf(file1, file2)
        app.run(args)
        assertEquals("diff: $file2: No such file or directory", stream.toString().trim())
    }
}
