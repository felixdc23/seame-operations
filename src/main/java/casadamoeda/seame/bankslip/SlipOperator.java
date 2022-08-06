package casadamoeda.seame.bankslip;

import casadamoeda.seame.order.OrderList;
import casadamoeda.seame.util.operator.ListItem;
import casadamoeda.seame.util.operator.Operator;
import casadamoeda.seame.util.operator.OperatorList;

import java.util.ArrayList;

public class SlipOperator extends Operator {
    private Slip bankSlip;

    public SlipOperator(String filename) {
        super(filename);
        int length = super.filename.length();
        if (super.filename.startsWith(".csv", length - 4)){
            this.bankSlip = new SlipCsv();
        } else if (super.filename.startsWith(".txt", length - 4)){
            this.bankSlip = new SlipTxt();
        }
    }



    public void LoadBankSlip() {
        this.bankSlip.GetList(this.filename);
    }

//    public void PrintBankSlipCSV() {
//        this.bankTextSlip.GetCsv().forEach(System.out::println);
//    }

    public void CreateBankslipCsvFile() {
        this.bankSlip.GenerateFile(this.filename);
    }

    @Override
    public ArrayList<ListItem> GetList() {
        return this.bankSlip.GetItemList();
    }

}
