package casadamoeda.seame.util;

import casadamoeda.seame.util.operator.ListItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExtractLines {
    private final ArrayList<ListItem> list = new ArrayList<>();
    String filename;

    public ExtractLines(String filename) {
        this.filename = filename;
        LoadExtractedLines(this.filename);
    }

    public ArrayList<ListItem> GetExtractedLines() {
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
                ListItem item = new ListItem(line);
                AddItem(item);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lines extracted.");
    }

    protected void AddItem(ListItem item) {
        this.list.add(item);
    }
}
