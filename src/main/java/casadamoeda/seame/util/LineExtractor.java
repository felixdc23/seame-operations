package casadamoeda.seame.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LineExtractor {
    private final ArrayList<String> list = new ArrayList<>();
    private String filename;

    public LineExtractor() {}

    public LineExtractor(String filename) {
        this.filename = filename;
        ExtractLines(this.filename);
    }

    private void LoadExtractedLines(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine().strip();

            while (line != null) {
                if (this.filename.startsWith(".txt", this.filename.length() - 4)) {
                    CsvConverter converter = new CsvConverter();
                    line = converter.GetCsv(line);
                }
                this.list.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lines extracted.");
    }

    public void ExtractLines(String filename) {
        LoadExtractedLines(filename);
    }

    public ArrayList<String> GetExtractedLines() {
        if (!this.list.isEmpty()) {
            return this.list;
        } else {
            return null;
        }

    }

}
