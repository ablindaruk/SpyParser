package olx.spy.parser;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NotesChecker {
    private final List<String> oldNotes;
    private final List<String> newNotes;

    public NotesChecker(List<String> oldNotes,List<String> newNotes) {
        this.oldNotes = oldNotes;
        this.newNotes = newNotes;
    }

    public StringBuffer checkNotesIdentity() {
        StringBuffer differanceNotes = new StringBuffer();
        if (oldNotes != null && newNotes != null) {
            Set<String> oldOne = new TreeSet<String>(oldNotes);
            Set<String> newOne = new TreeSet<String>(newNotes);

            Set<String> difference = Sets.difference(newOne, oldOne);

            String infoNotes = "difference between old and new notes: " + difference.size() + "\n\n";
            differanceNotes.append(infoNotes);
            differanceNotes.append(splitNotes(difference));
        } else {
            return new StringBuffer();
        }
        return differanceNotes;
    }

    private String splitNotes(Set<String> difference) {
        String strNotes = difference.toString();
        strNotes = strNotes.replaceAll(" - to visit follow to this link ",
                " - to visit follow to this link \n");
        return strNotes;
    }
}
