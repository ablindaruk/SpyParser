package olx.spy.parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class RealEstateFileCreator {
    private final String directoryPath = "src\\main\\resources\\";
    private StringBuffer realEstateNotes;

    public RealEstateFileCreator(StringBuffer realEstateNotes) {
        this.realEstateNotes = realEstateNotes;
    }

    public void setRealEstateNotes(StringBuffer realEstateNotes) {
        this.realEstateNotes = realEstateNotes;
    }

    public void writeRealEstateNotes(String fileNameToWriteInto) {
        String filepath = directoryPath + fileNameToWriteInto;
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath),
                    StandardCharsets.UTF_8));
            writer.write(realEstateNotes.toString());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException | AssertionError e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> readRealEstateNotes(String fileToReadFrom) {
        String filepath = directoryPath + fileToReadFrom;
        List<String> notesSet = new LinkedList<>();
        File file = new File(filepath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                String note;
                while ((note = reader.readLine()) != null) {
                    notesSet.add(note);
                }
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Possibly it is first call and file not found at path: " + filepath);
        }
        return notesSet;
    }
}
