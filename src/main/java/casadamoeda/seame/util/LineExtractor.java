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
        extractLines(this.filename);
    }

    private void loadExtractedLines(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine().strip();

            while (line != null) {
                if (this.filename.startsWith(".txt", this.filename.length() - 4)) {
                    CsvConverter converter = new CsvConverter();
                    line = converter.getCsv(line);
                }
                this.list.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lines extracted.");
    }

    public void extractLines(String filename) {
        loadExtractedLines(filename);
    }

    public ArrayList<String> getExtractedLines() {
        if (!this.list.isEmpty()) {
            return this.list;
        } else {
            return null;
        }

    }

}
