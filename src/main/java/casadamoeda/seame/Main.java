package casadamoeda.seame;

import casadamoeda.seame.util.CsvFileCreator;
import casadamoeda.seame.util.TableMerger;
import casadamoeda.seame.operator.Operator;

public class Main {
    public static void main(String[] args) {

        Operator bankslipOperator = new Operator("data/", "boletos-20220810.txt");

        bankslipOperator.LoadItems();

        bankslipOperator.AddHeader("\"numero\";\"nome\";\"data de criacao\";\"data de pagamento\";\"valor\";\"col05\";\"col06\";\"col07\";\"col08\";\"col9\";\"total\";\"col11\";\"col12\"");

        bankslipOperator.CreateCsvFile();

//        Operator orderOperator = new Operator("data/", "PedidosCM_2022_08_10.csv");
        Operator orderOperator = new Operator("data/", "faturados-20220811.csv");

        orderOperator.LoadItems();

        orderOperator.CreateCsvFile();

//        Operator table1 = new Operator("staging/", "PedidosCM_2022_08_10.csv");
        Operator table1 = new Operator("staging/", bankslipOperator.filename.substring(0, bankslipOperator.filename.length() - 4) + ".csv");
        table1.LoadItems();

        Operator table2 = new Operator("staging/", "faturados-20220811.csv");

        table2.LoadItems();

        TableMerger tableMerger = new TableMerger(table1.GetItemList(), table2.GetItemList());

        tableMerger.SelectHeaders("A", new Integer[]{0, 1, 2, 3, 4, 10});

        tableMerger.SelectHeaders("B", new Integer[]{0, 2, 3, 4, 5, 6});

        tableMerger.Merge(0, 0);

        CsvFileCreator csv = new CsvFileCreator("output/", "merged" + bankslipOperator.filename, tableMerger.GetMerged());
        csv.GenerateFile();

    }
}