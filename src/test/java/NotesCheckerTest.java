import olx.spy.parser.NotesChecker;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

public class NotesCheckerTest {
    private NotesChecker notesChecker;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        List<String> oneNoteList = null;
        List<String> twoToteList = null;
        notesChecker = new NotesChecker(oneNoteList, twoToteList);
    }

    @Test
    public void testCheckNotesIdentityWithNullInLists() {
        notesChecker.checkNotesIdentity();
    }

    @AfterClass
    public static void finish() {
        System.out.println("Tests finished");
    }
}
