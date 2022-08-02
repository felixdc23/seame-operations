package br.gov.casadamoeda.seame.bankslips;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Operator {
    private final String filename;
    private final Slip bankSlip = new Slip();

    public Operator(String filename) {
        this.filename = filename;
    }

    public void loadBankSlip() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + this.filename))) {

            String line = br.readLine();

            while (line != null) {
                this.bankSlip.AddLine(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void printBankSlip() {
//        this.bankSlip.DdBankSlip();
//    }

    public void printBankSlipCSV() {
        this.bankSlip.GetCsv().forEach(System.out::println);
    }

}