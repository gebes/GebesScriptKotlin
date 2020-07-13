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
             // intends
              # with the
               // comments
                # which looks
                 # really cool
                  // this should work quite deep
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
    fun stripCommentsTest4() {

        val input = """
            main
             println
              Hello World // comments will be printed
              // here not
        """.trimIndent()

        val exceptedOutput = """
            main
             println
              Hello World // comments will be printed
        """.trimIndent()

        val output = CommentStripper.strip(input.split("\n"))
            .joinToString(separator = "\n")

        assertEquals(output, exceptedOutput);

    }
}