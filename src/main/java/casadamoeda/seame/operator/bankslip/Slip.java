package casadamoeda.seame.operator.bankslip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Slip {
    final List<String> entries = new ArrayList<>();

    private void AddLine(String line) {
        this.entries.add(line);
    }

    protected void AddHeader(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            String line = br.readLine();

            if (line != null) {
                this.entries.add(0, line);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void ExtractLines(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            AddHeader("bankslipheader.csv");
            String line = br.readLine();

            while (line != null) {
                AddLine(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String GetHeader() {
        return this.entries.get(0);
    }

    protected void GenerateFile(String filename) {
        if (!this.entries.isEmpty()) {
            try {
                File file = new File("output/" + filename.substring(0, filename.length() - 4) + ".csv");
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    this.entries.forEach(s -> {
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
