import olx.spy.parser.NotesChecker;
import olx.spy.parser.RealEstateFileCreator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

public class RealEstateFileCreatorTest {
    private RealEstateFileCreator realEstateFileCreator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        realEstateFileCreator = new RealEstateFileCreator(null);
    }

    @Test
    public void testWriteRealEstateNotesIfNullPassedIntoConstructor() {
        realEstateFileCreator.writeRealEstateNotes("testnotes.txt");
    }

    @Test
    public void testWriteRealEstateNotesIfNullPassedIntoSetter() {
        new RealEstateFileCreator(new StringBuffer("a note to test"));
        realEstateFileCreator.setRealEstateNotes(null);
    }

    @Test
    public void testWriteRealEstateNotesIfNullPassedIntoWriter() {
        new RealEstateFileCreator(new StringBuffer("a note to test"));
        realEstateFileCreator.writeRealEstateNotes(null);
    }

    @Test
    public void testWriteRealEstateNotesIfNullPassedIntoReader() {
        realEstateFileCreator.readRealEstateNotes(null);
    }

    @AfterClass
    public static void finish() {
        System.out.println("Tests finished");
    }
}
