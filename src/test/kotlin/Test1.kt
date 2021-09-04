import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


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
        val primaryPathDirectory = "data/test"
        for (i in 1..10) {
            val pathDirectory = "$primaryPathDirectory$i/"
            val pathFileOrigin ="${pathDirectory}origin.txt"
            val pathFileNew = "${pathDirectory}new.txt"
            val fileDiff = File("${pathDirectory}diff.txt")
            val textFileDiff = fileDiff.readText()

            val args = arrayOf<String>(pathFileOrigin, pathFileNew)

            main(args)
            assertEquals(textFileDiff, stream.toString().trim())
        }
    }
}
