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

        val output = CommentStripper.strip(input.split("\n"))
            .joinToString(separator = "\n")
        assertEquals(output, exceptedOutput);

    }

    @Test
    fun stripCommentsTest2() {

        val input = """
        
            // other comment type
            // with blank lines
            
            main
        
        """.trimIndent()

        val exceptedOutput = """
            main
        """.trimIndent()

        val output = CommentStripper.strip(input.split("\n"))
            .joinToString(separator = "\n")
        assertEquals(output, exceptedOutput);

    }

    @Test
    fun stripCommentsTest3() {

        val input = """
            // comments will only be accepted with intends
            # intend means comment will stay
             # like this
            main
        """.trimIndent()

        val exceptedOutput = """
             # like this
            main
        """.trimIndent()

        val output = CommentStripper.strip(input.split("\n"))
            .joinToString(separator = "\n")
        assertEquals(output, exceptedOutput);

    }

    @Test
    fun stripCommentsTest4() {

        val input = """
            main
             println
              Hello World // comments will be printed
              // here also, because those arent comments
        """.trimIndent()

        val exceptedOutput = """
            main
             println
              Hello World // comments will be printed
              // here also, because those arent comments
        """.trimIndent()

        val output = CommentStripper.strip(input.split("\n"))
            .joinToString(separator = "\n")

        assertEquals(output, exceptedOutput);

    }
}