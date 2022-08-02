package br.gov.casadamoeda.seame.bankslips;

import java.util.ArrayList;
import java.util.List;

public class Slip {

    private final List<String> entries = new ArrayList<>();

    public void AddLine(String line) {
        this.entries.add(line);
    }

//    public void DdBankSlip() {
//        this.entries.forEach(System.out::println);
//    }

    public List<String> GetCsv() {

        List<String> csvList = new ArrayList<>();
        Converter converter = new Converter();

        this.entries.forEach(s -> csvList.add(converter.GetCsv(s)));

        return csvList;
    }

}
