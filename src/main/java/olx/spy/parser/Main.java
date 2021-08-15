package olx.spy.parser;

import java.util.List;

public class Main implements PagesOlx {
    private final static String oldNotesFile = "old_notes.txt";
    private final static String newNotesFile = "new_notes.txt";
    private final static String differNotesFile = "differ_notes.txt";

    public static void main(String[] args) {
        String[] pagesRealEstate = {pageFlatsRooms, pageHouses, pageGarages, pageCommerce,
                pageFlatsAbroad, pageHousesAbroad, pageCommerceAbroad};
        try {
            //get parse notes
            OlxParser parserOlx = new OlxParser(pagesRealEstate);
            StringBuffer realEstateNotes = parserOlx.parseOlxPages();

            //write new parse notes
            RealEstateFileCreator realEstateFileCreator = new RealEstateFileCreator(realEstateNotes);
            realEstateFileCreator.writeRealEstateNotes(newNotesFile);

            //get old parse notes list
            List<String> oldNotesSet = realEstateFileCreator.readRealEstateNotes(oldNotesFile);

            //get new notes list
            List<String> newNotesSet = realEstateFileCreator.readRealEstateNotes(newNotesFile);

            //look for new notes
            NotesChecker notesChecker = new NotesChecker(oldNotesSet, newNotesSet);
            StringBuffer notesDifferance = notesChecker.checkNotesIdentity();

            //write only new notes that appeared
            realEstateFileCreator.setRealEstateNotes(notesDifferance);
            realEstateFileCreator.writeRealEstateNotes(differNotesFile);

            //write all latest received notes
            realEstateFileCreator.setRealEstateNotes(realEstateNotes);
            realEstateFileCreator.writeRealEstateNotes(oldNotesFile);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}


