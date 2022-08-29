package casadamoeda.seame;

import casadamoeda.seame.util.CsvFileCreator;
import casadamoeda.seame.util.TableMerger;
import casadamoeda.seame.operator.Operator;

public class Main {
    public static void main(String[] args) {

        Operator bankslipOperator = new Operator("data/", "boletos-20220829.txt");

        bankslipOperator.loadItems();

        bankslipOperator.addHeader("\"numero\";\"nome\";\"vencimento\";\"data de pagamento\";\"valor\";\"col05\";\"col06\";\"col07\";\"col08\";\"col9\";\"total\";\"col11\";\"col12\"");

        bankslipOperator.createCsvFile();

//        Operator orderOperator = new Operator("data/", "PedidosCM_2022_08_10.csv");
        Operator orderOperator = new Operator("data/", "faturados-20220829.csv");

        orderOperator.loadItems();

        orderOperator.createCsvFile();

//        Operator table1 = new Operator("staging/", "PedidosCM_2022_08_10.csv");
        Operator table1 = new Operator("staging/", bankslipOperator.getFilename().substring(0, bankslipOperator.getFilename().length() - 4) + ".csv");
        table1.loadItems();

        Operator table2 = new Operator("staging/", "faturados-20220829.csv");

        table2.loadItems();

        TableMerger tableMerger = new TableMerger(table1.getItemList(), table2.getItemList());

        tableMerger.selectHeaders("A", new Integer[]{0, 1, 2, 3, 4, 8, 10});

        tableMerger.selectHeaders("B", new Integer[]{0, 2, 3, 4, 5, 6});

        tableMerger.merge(0, 0);

        CsvFileCreator csv = new CsvFileCreator("output/", "merged" + bankslipOperator.getFilename(), tableMerger.getMerged());
        csv.generateFile();

    }
}