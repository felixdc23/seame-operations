package br.gov.casadamoeda.seame;

public class Main {
    public static void main(String[] args) {

//        System.out.println("Hello world!");

        Operator extrato = new Operator("boletos.txt");

        extrato.loadBankSlip();

        extrato.printBankSlipCSV();
    }
}