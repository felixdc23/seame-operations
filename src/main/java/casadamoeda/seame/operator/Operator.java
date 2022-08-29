package casadamoeda.seame.operator;

import casadamoeda.seame.util.CsvFileCreator;

import java.util.ArrayList;

public class Operator {
    private final String filepath;
    private final String filename;
    private final ItemList itemList = new ItemList();


    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public Operator(String path, String filename) {
        this.filepath = path;
        this.filename = filename;
    }

    public void loadItems() {
        this.itemList.loadItemList(this.filepath + this.filename);
    }

    public void addHeader(String header) {
        this.itemList.addItem(0, new ListItem(header));
    }

    public void PrintItemList() {
        this.itemList.items.forEach(System.out::println);
    }

    public void createCsvFile() {
        CsvFileCreator csv = new CsvFileCreator("staging/", this.filename, this.itemList.getItemList());
        csv.generateFile();
    }

    public ArrayList<ListItem> getItemList() {
        return this.itemList.items;
    }

}
