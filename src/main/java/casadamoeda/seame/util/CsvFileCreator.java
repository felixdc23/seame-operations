package casadamoeda.seame.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvFileCreator {
    private final String sourcePath;
    private final String sourceFile;
    private final String filepath;

    public String getFilepathName() {
        return filepath;
    }

    public CsvFileCreator(String sourcePath, String sourceFile, String filepath) {
        this.sourcePath = sourcePath;
        this.sourceFile = sourceFile;
        this.filepath = filepath;
    }

    public void createFile() {
        try {
            String filePath = this.filepath + this.sourceFile.substring(0, this.sourceFile.length() - 4) + ".csv";
            Path pathFilename = Paths.get(filePath);
            try {
                Files.delete(pathFilename);
            } catch (NoSuchFileException ignored) { }

            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);

            String sourcePath = this.sourcePath + this.sourceFile;

            try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {
                String line = br.readLine().strip();

                while (line != null) {
                    if (this.sourceFile.startsWith(".txt", this.sourceFile.length() - 4)) {
                        CsvConverter converter = new CsvConverter();
                        line = converter.getCsv(line);
                    }
                    try {
                        fileWriter.write(line + System.getProperty("line.separator"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    line = br.readLine();

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
   }

}
