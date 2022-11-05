package casadamoeda.seame.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileFormat {
    private String filepath;
    private String prefix = "f-";
    private String filename;
    private int lineLength;

    public TextFileFormat() {
    }

    public TextFileFormat(String filepath, String filename, int lineLength) {
        this.filepath = filepath;
        this.filename = filename;
        this.lineLength = lineLength;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        if (!prefix.equals("")) this.prefix = prefix;
    }

    public String getFilename() {
        return prefix + filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public void formatFile() throws IOException {
        Path pathFilename = Paths.get(this.filepath + this.prefix + this.filename);
        try {
            Files.delete(pathFilename);
        } catch (NoSuchFileException ignored) {
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath + this.filename))) {
            String line = br.readLine().strip();

            sb.append(line);

            while (line != null) {
                if (line.split(" ")[0].length() != this.lineLength) {
                    sb.append(" ");
                } else {
                    sb.append(System.lineSeparator());
                    writeLine(sb.toString(), pathFilename.toString());
                    sb.setLength(0);
                }

                sb.append(line.strip());
                line = br.readLine();
            }
        }
    }

    public void writeLine(String line, String pathFilename) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(pathFilename, true));
        br.append(line);
        br.close();
    }

}
