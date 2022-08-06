package casadamoeda.seame.bankslip;

import casadamoeda.seame.util.operator.ListItem;
import casadamoeda.seame.util.operator.OperatorList;
import casadamoeda.seame.util.ExtractLines;

import java.io.*;
import java.util.ArrayList;

public abstract class Slip extends OperatorList {

//    protected void AddHeader(String filename) {
//        ExtractLines extractor = new ExtractLines(filename);
//        this.items.add(0, extractor.GetExtractedLines().get(0));
//    }

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
