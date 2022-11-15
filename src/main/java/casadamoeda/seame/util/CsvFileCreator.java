package casadamoeda.seame.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileCreator {
    private final String sourcePath;
    private final String sourceFile;
    private final String filepath;

    public String getFilePath() {
        return this.filepath;
    }

    public String getFileName() {
        return this.sourceFile.substring(0, this.sourceFile.length() - 4) + ".csv";
    }

    public CsvFileCreator(String sourcePath, String sourceFile, String filepath) {
        this.sourcePath = sourcePath;
        this.sourceFile = sourceFile;
        this.filepath = filepath;
    }

    private String trimLine(String line) {
        List<String> trimmedLine;

        trimmedLine = Arrays.stream(line.split(";")).toList();

        trimmedLine = trimmedLine.stream().map(String::trim).collect(Collectors.toList());

        return String.join(";", trimmedLine);
    }

    public void createFile() {
        try {
            String filePath = getFilePath() + getFileName();
            Path pathFilename = Paths.get(filePath);
            try {
                Files.delete(pathFilename);
            } catch (NoSuchFileException ignored) { }

            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);

            String sourcePath = this.sourcePath + this.sourceFile;

            try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {
                String line = br.readLine();

                while (line != null) {
                    if (this.sourceFile.startsWith(".txt", this.sourceFile.length() - 4)) {
                        CsvConverter converter = new CsvConverter();
                        line = converter.getCsv(line);
                    }
                    try {
                        fileWriter.write(trimLine(line) + System.getProperty("line.separator"));
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
