package casadamoeda.seame;

import casadamoeda.seame.util.CsvFileCreator;
import casadamoeda.seame.util.TableMerger;
import casadamoeda.seame.operator.Operator;
import casadamoeda.seame.util.TextFileFormat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {



        String slipFilename = "boletos-20221104.txt";
        String salesReportFilename = "faturados-20221103.csv";
        String dataFolder = "data/";
        String stagingFolder = "staging/";
        String outputFolder = "output/";

        TextFileFormat fileFormat = new TextFileFormat(dataFolder, slipFilename, 19);
        fileFormat.formatFile();
        slipFilename = fileFormat.getFilename();

        Operator bankslipOperator = new Operator(dataFolder, slipFilename);

        bankslipOperator.loadItems();

        bankslipOperator.addHeader("\"numero\";\"nome\";\"vencimento\";\"data de pagamento\";\"valor\";\"col05\";\"col06\";\"col07\";\"col08\";\"col9\";\"total\";\"col11\";\"col12\"");

        bankslipOperator.createCsvFile();

//        Operator orderOperator = new Operator("data/", "PedidosCM_2022_08_10.csv");
        Operator orderOperator = new Operator(dataFolder, salesReportFilename);

        orderOperator.loadItems();

        orderOperator.createCsvFile();

//        Operator table1 = new Operator("staging/", "PedidosCM_2022_08_10.csv");
        Operator table1 = new Operator(stagingFolder, bankslipOperator.getFilename().substring(0, bankslipOperator.getFilename().length() - 4) + ".csv");
        table1.loadItems();

        Operator table2 = new Operator(stagingFolder, salesReportFilename);

        table2.loadItems();

        TableMerger tableMerger = new TableMerger(table1.getItemList(), table2.getItemList());

        tableMerger.selectHeaders("A", new Integer[]{0, 1, 2, 3, 4, 8, 10});

        tableMerger.selectHeaders("B", new Integer[]{0, 2, 3, 4, 5, 6});

        tableMerger.merge(0, 0);

        CsvFileCreator csv = new CsvFileCreator(outputFolder, "merged" + bankslipOperator.getFilename(), tableMerger.getMerged());
        csv.generateFile();


    }
}