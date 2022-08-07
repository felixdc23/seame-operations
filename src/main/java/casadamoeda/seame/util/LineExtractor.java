package casadamoeda.seame.util;

import casadamoeda.seame.operator.ListItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LineExtractor {
    private final ArrayList<String> list = new ArrayList<>();
    String filename;

    public LineExtractor(String filename) {
        this.filename = filename;
        LoadExtractedLines(this.filename);
    }

    public ArrayList<String> GetExtractedLines() {
        if (!this.list.isEmpty()) {
            return this.list;
        } else {
            return null;
        }

    }

    private void LoadExtractedLines(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            String line = br.readLine();

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

}
