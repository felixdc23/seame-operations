package casadamoeda.seame.operator.bankslip;

import casadamoeda.seame.operator.Operator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SlipOperator extends Operator {
    private Slip bankSlip = new Slip();

    public SlipOperator(String filename) {
        super(filename);
    }

    public void LoadBankSlip() {
        this.bankSlip.ExtractLines(this.filename);
    }

    public void PrintBankSlipCSV() {
        this.bankSlip.GetCsv().forEach(System.out::println);
    }

    public void CreateBankslipCsvFile() {
        if (!this.bankSlip.GetCsv().isEmpty()) {
            try {
                File file = new File("output/" + this.filename.substring(0, this.filename.length() - 4) + ".csv");
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    this.bankSlip.GetCsv().forEach(s -> {
                        try {
                            StringBuilder sb = new StringBuilder(s);
                            sb.append(System.getProperty("line.separator"));
                            fileWriter.write(sb.toString());
                            System.out.println(sb);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
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
