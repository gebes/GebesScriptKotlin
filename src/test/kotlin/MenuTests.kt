import eu.gebes.utils.Title
import eu.gebes.utils.TitlePrinter
import eu.gebes.utils.VersionManager
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MenuTests {

    /**
     * Makes sure that the version is printed
     */
    @Test
    fun versionTest1(): Unit {
        assert(
            Title.title.contains(VersionManager.VERSION)
                    or
                    Title.subtitle.contains(VersionManager.VERSION)
        )
    }

}