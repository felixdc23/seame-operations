package br.gov.casadamoeda.seame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Operator {
    private String filename;
    private Slip bankSlip = new Slip();

    public Operator(String filename) {
        this.filename = filename;
    }

    public void loadBankSlip() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + this.filename))) {

//            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                this.bankSlip.AddLine(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printBankSlip() {
        this.bankSlip.DdBankSlip();
    }

    public void printBankSlipCSV() {
        this.bankSlip.GetCsv().forEach(System.out::println);
    }

}