package casadamoeda.seame.operator;

import casadamoeda.seame.util.CsvFileCreator;

import java.util.ArrayList;

public class Operator {
    public final String filepath;
    public final String filename;
    private final ItemList itemList = new ItemList();



    public Operator(String path, String filename) {
        this.filepath = path;
        this.filename = filename;
    }

    public void LoadItems() {
        this.itemList.LoadItemList(this.filepath + this.filename);
    }

    public void AddHeader(String header) {
        this.itemList.AddItem(0, new ListItem(header));
    }

    public void PrintItemList() {
        this.itemList.items.forEach(System.out::println);
    }

    public void CreateCsvFile() {
        CsvFileCreator csv = new CsvFileCreator("staging/", this.filename, this.itemList.GetItemList());
        csv.GenerateFile();
    }

    public ArrayList<ListItem> GetItemList() {
        return this.itemList.items;
    }

}
