package casadamoeda.seame;

import casadamoeda.seame.util.Merger;
import casadamoeda.seame.operator.Operator;

public class Main {
    public static void main(String[] args) {

        Operator bankslipOperator = new Operator("boletos-20220802.txt");

        bankslipOperator.LoadOrders();

        System.out.println(bankslipOperator);

        bankslipOperator.CreateCsvFile();

        Operator orderOperator = new Operator("pedidos-teste.csv");

        orderOperator.LoadOrders();

        orderOperator.CreateCsvFile();


        Merger merger = new Merger(orderOperator.GetItemList(), bankslipOperator.GetItemList());

        merger.SelectHeaders("A", new Integer[]{0,5,6,7,8,9,13,14,16,17,18});

        merger.SelectHeaders("B", new Integer[]{0,1,2,3,4,10});

        merger.PrintTable("B");

        merger.Merge(0, 0);
    }
}