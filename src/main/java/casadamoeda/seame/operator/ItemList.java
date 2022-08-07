package casadamoeda.seame.operator;

import casadamoeda.seame.util.LineExtractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemList {
    protected final ArrayList<ListItem> items = new ArrayList<>();

    public void LoadItemList(String filename) {
        LineExtractor extractor = new LineExtractor(filename);

        ArrayList<String> lines = extractor.GetExtractedLines();

        lines = RemoveDuplicates(lines);

        lines.forEach(s -> this.items.add(new ListItem(s)));
    }

    public ArrayList<String> RemoveDuplicates(ArrayList<String> list) {
        Set<String> set = new HashSet<>(list);

        list.clear();

        list.addAll(set);

        return list;
    }

    protected ListItem GetHeader() {
        if (!this.items.isEmpty()) {
            return this.items.get(0);
        } else {
            return null;
        }
    }

    protected void GenerateFile(String filename) {
        if (!this.items.isEmpty()) {
            try {
                File file = new File("output/" + filename.substring(0, filename.length() - 4) + ".csv");
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    this.items.forEach(s -> {
                        try {
                            fileWriter.write(s + System.getProperty("line.separator"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    fileWriter.close();
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

}