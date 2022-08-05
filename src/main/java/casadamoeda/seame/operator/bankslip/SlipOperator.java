package casadamoeda.seame.operator.bankslip;

import casadamoeda.seame.operator.Operator;

public class SlipOperator extends Operator {
    private Slip bankTextSlip;

    public SlipOperator(String filename) {
        super(filename);
        int length = super.filename.length();
        if (super.filename.startsWith(".csv", length - 4)){
            this.bankTextSlip = new CsvSlip();
        } else if (super.filename.startsWith(".txt", length - 4)){
            this.bankTextSlip = new TxtSlip();
        }
    }

    public void LoadBankSlip() {
        this.bankTextSlip.ExtractLines(this.filename);
    }

//    public void PrintBankSlipCSV() {
//        this.bankTextSlip.GetCsv().forEach(System.out::println);
//    }

    public void CreateBankslipCsvFile() {
        this.bankTextSlip.GenerateFile(this.filename);
    }
}
