import eu.gebes.script.CommentStripper
import eu.gebes.script.ScriptFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScriptFileTest {

    @Test
    fun stripCommentsTest() {

        val input = """
            # Some Test comment
            main
        """.trimIndent()

        val exceptedOutput = """
            main
        """.trimIndent()

        val output = CommentStripper.strip(input.split("\n")).joinToString()

        assertEquals(output, exceptedOutput);


    }


}