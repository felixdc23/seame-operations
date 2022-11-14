package casadamoeda.seame.util;

import casadamoeda.seame.operator.ListItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class FileMerger {

    private final String pathFilename1;
    private final Integer[] file1Headers;
    private final String pathFilename2;
    private final Integer[] file2Headers;
    private final String outputPath;

    public FileMerger(String pathFilename1,Integer[] file1Headers, String pathFilename2, Integer[] file2Headers, String outputPath) {
        this.pathFilename1 = pathFilename1;
        this.file1Headers = file1Headers;
        this.pathFilename2 = pathFilename2;
        this.file2Headers = file2Headers;
        this.outputPath = outputPath;
    }

    private Boolean checkFileType(String pathFilename, String fileExtension) {
        return pathFilename.substring(pathFilename.length() - 4, 3).equals(".csv");
    }

    public void merge(Integer colFile1, Integer colFile2) {
        try {
            String outputFile = this.outputPath
                    + "merged-"
                    + pathFilename1.substring(pathFilename1.length() - 4)
                    + ".csv";

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

                try (BufferedReader brFile1 = new BufferedReader(new FileReader(this.pathFilename1))) {

                    String file1Line = brFile1.readLine();
                    while (file1Line != null) {
                        String[] line1 = file1Line.split(";");

                        try (BufferedReader brFile2 = new BufferedReader(new FileReader(this.pathFilename2))) {
                            String file2Line = brFile2.readLine();

                            while (file2Line != null) {
                                String[] line2 = file1Line.split(";");
                                if(line1[colFile1].equals(line2[colFile2])) {
                                    StringBuilder sb = new StringBuilder();
                                    Arrays.stream(this.file1Headers).forEach(s->{
                                        sb.append(line1[s]).append(";");
                                    });
                                    Arrays.stream(this.file2Headers).forEach(s->{
                                        sb.append(line2[s]).append(";");
                                    });

                                    fileWriter.write(sb + System.lineSeparator());
                                }
                            }
                        }
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}