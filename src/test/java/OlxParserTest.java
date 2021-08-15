import olx.spy.parser.OlxParser;
import olx.spy.parser.RealEstateFileCreator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OlxParserTest {
    private OlxParser olxParser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        olxParser = new OlxParser(null);
    }

    @Test
    public void testOlxParserIfNullPassedIntoConstructor() {
        olxParser.parseOlxPages();
    }

    @Test
    public void testOlxParserIfWrongUrlsTypeTypePassedIntoConstructor() {
        String[] notes = new String[2];
        notes[0] = "123";
        notes[1] = "abc";
        olxParser = new OlxParser(notes);
        olxParser.parseOlxPages();
    }

    @AfterClass
    public static void finish() {
        System.out.println("Tests finished");
    }
}
