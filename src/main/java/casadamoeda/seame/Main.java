package casadamoeda.seame;

import casadamoeda.seame.bankslip.SlipOperator;
import casadamoeda.seame.order.OrderOperator;
import casadamoeda.seame.util.Merger;

public class Main {
    public static void main(String[] args) {

        SlipOperator bankslipOperator = new SlipOperator("boletos-20220805.csv");

        bankslipOperator.LoadBankSlip();

//        System.out.println(bankslipOperator);

//        bankslipOperator.PrintBankSlipCSV();

//        bankslipOperator.CreateBankslipCsvFile();

        OrderOperator orderOperator = new OrderOperator("Pedidos_CM_2022_08_04.csv");

        orderOperator.LoadOrders();

//        orderOperator.PrintOrderList();

        Merger merger = new Merger(orderOperator.GetList(), bankslipOperator.GetList());

        merger.PrintTableB();
    }
}