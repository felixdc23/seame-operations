package casadamoeda.seame.bankslip;

import casadamoeda.seame.util.Converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SlipTxt extends Slip {
    @Override
    protected void GenerateFile(String filename) {
        Converter converter = new Converter();
        if (!super.items.isEmpty()) {
            try {
                File file = new File("output/" + filename.substring(0, filename.length() - 4) + ".csv");
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(super.items.get(0).toString());
                    super.items.stream().skip(1).forEach(s -> {
                        try {
                            fileWriter.write(System.getProperty("line.separator") + converter.GetCsv(s.toString()));
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
