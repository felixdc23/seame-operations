package casadamoeda.seame.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvFileCreator {
    private final String filepath;
    private final String filename;
    private final ArrayList<String> lines;

    public CsvFileCreator(String filepath, String filename, ArrayList<String> lines) {
        this.filepath = filepath;
        this.filename = filename;
        this.lines = lines;
    }

    protected void createFile(String pathname) {
        if (!this.lines.isEmpty()) {
            try {

                String filePath = pathname.substring(0, pathname.length() - 4) + ".csv";
                Path pathFilename = Paths.get(filePath);
                try {
                    Files.delete(pathFilename);
                } catch (NoSuchFileException ignored) {
                }

                File file = new File(filePath);
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    this.lines.forEach(s -> {
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

    public void generateFile() {
        createFile(this.filepath + this.filename);
    }
}
