package casadamoeda.seame.operator.bankslip;

import casadamoeda.seame.util.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Slip {

    private final List<String> entries = new ArrayList<>();

    private void AddLine(String line) {
        this.entries.add(line);
    }

//    public void DdBankSlip() {
//        this.entries.forEach(System.out::println);
//    }

    protected void ExtractLines(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            String line = br.readLine();

            while (line != null) {
                AddLine(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> GetCsv() {
        List<String> csvList = new ArrayList<>();

        if (!this.entries.isEmpty()) {
            Converter converter = new Converter();

            this.entries.forEach(s -> csvList.add(converter.GetCsv(s)));
        }

        return csvList;
    }

}
