package br.gov.casadamoeda.seame;

import br.gov.casadamoeda.seame.bankslips.Operator;

public class Main {
    public static void main(String[] args) {

        Operator operator = new Operator("boletos.txt");

        operator.loadBankSlip();

        operator.printBankSlipCSV();
    }
}