package casadamoeda.seame.operator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Operator {
    private final String filename;

    private final ItemList itemList = new ItemList();

    public Operator(String filename) {
        this.filename = filename;
    }

    public void LoadOrders() {
        this.itemList.LoadItemList(this.filename);
    }

    public void PrintItemList() {
        this.itemList.items.forEach(System.out::println);
    }

    public ArrayList<ListItem> GetItemList() {
        return this.itemList.items;
    }

    public void CreateCsvFile() {
        this.itemList.GenerateFile(this.filename);
    }

}
