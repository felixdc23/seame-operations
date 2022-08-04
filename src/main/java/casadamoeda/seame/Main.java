package casadamoeda.seame;

import casadamoeda.seame.operator.bankslip.SlipOperator;
import casadamoeda.seame.operator.order.OrderOperator;

public class Main {
    public static void main(String[] args) {

        SlipOperator bankslipOperator = new SlipOperator("boletos-20220803.txt");

        bankslipOperator.LoadBankSlip();

        bankslipOperator.PrintBankSlipCSV();

        OrderOperator orderOperator = new OrderOperator("Pedidos_CM_2022_08_04.csv");

        orderOperator.LoadOrders();

        orderOperator.PrintOrderList();
    }
}