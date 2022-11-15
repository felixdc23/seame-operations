package casadamoeda.seame.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileMerger {

    private final String pathFile1;
    private final String nameFile1;
    private final Integer[] file1Headers;
    private final String pathFile2;
    private final String nameFile2;
    private final Integer[] file2Headers;
    private final String outputPath;

    public FileMerger(String pathFile1, String nameFile1, Integer[] file1Headers, String pathFile2, String nameFile2, Integer[] file2Headers, String outputPath) {
        this.pathFile1 = pathFile1;
        this.nameFile1 = nameFile1;
        this.file1Headers = file1Headers;
        this.pathFile2 = pathFile2;
        this.nameFile2 = nameFile2;
        this.file2Headers = file2Headers;
        this.outputPath = outputPath;
    }

    private String getPathFileName(Integer file) {
        if (file.equals(0)) {
            return this.pathFile1 + this.nameFile1;
        } else if (file.equals(1)) {
            return this.pathFile2 + this.nameFile2;
        } else {
            return "";
        }
    }

    private Boolean checkFileType(String pathFilename, String fileExtension) {
        return pathFilename.substring(pathFilename.length() - 4, 3).equals(".csv");
    }

    public void merge(Integer colFile1, Integer colFile2) {
        try {
            String outputFile = this.outputPath + "merged-" + this.nameFile1;

            Path pathFilename = Paths.get(outputFile);
            try {
                Files.delete(pathFilename);
            } catch (NoSuchFileException ignored) {

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {

                File file = new File(outputFile);
                FileWriter fileWriter = new FileWriter(file);

                try (BufferedReader brFile1 = new BufferedReader(new FileReader(getPathFileName(0)))) {

                    String file1Line = brFile1.readLine();

                    while (file1Line != null) {
                        String[] line1 = file1Line.split(";");

                        try (BufferedReader brFile2 = new BufferedReader(new FileReader(getPathFileName(1)))) {
                            String file2Line = brFile2.readLine();

                            while (file2Line != null) {
                                String[] line2 = file2Line.split(";");
                                if (line1[colFile1].equals(line2[colFile2].trim())) {
                                    StringBuilder sb = new StringBuilder();
                                    Arrays.stream(this.file1Headers).forEach(s -> {
                                        sb.append(line1[s]).append(";");
                                    });
                                    Arrays.stream(this.file2Headers).forEach(s -> {
                                        sb.append(line2[s]).append(";");
                                    });

                                    fileWriter.write(sb + System.lineSeparator());

                                }

                                file2Line = brFile2.readLine();

                            }
                        }

                        file1Line = brFile1.readLine();

                    }

                }

                fileWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}