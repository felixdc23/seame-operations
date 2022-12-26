package casadamoeda.seame;

import casadamoeda.seame.util.CsvFileCreator;
import casadamoeda.seame.util.FileMerger;
import casadamoeda.seame.util.TextFileFormat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        String slipFilename = "boletos-20221226.txt";
        String salesReportFilename = "faturados-20221226.csv";
        String dataFolder = "data/";

        TextFileFormat fileFormat = new TextFileFormat(dataFolder, slipFilename, 19);
        fileFormat.formatFile();
        slipFilename = fileFormat.getFilename();

        CsvFileCreator csvBankslip = new CsvFileCreator("data/", slipFilename, "staging/");
        csvBankslip.createFile();

        CsvFileCreator csvSalesRep = new CsvFileCreator("data/", salesReportFilename, "staging/");
        csvSalesRep.createFile();

        FileMerger merger = new FileMerger(
                csvBankslip.getFilePath(),
                csvBankslip.getFileName(),
                new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                csvSalesRep.getFilePath(),
                csvSalesRep.getFileName(),
                new Integer[]{0, 1, 2, 3, 4, 5, 6},
                "output/");

        merger.merge(0, 0);


    }
}