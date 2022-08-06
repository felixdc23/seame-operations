package casadamoeda.seame.util.operator;

import casadamoeda.seame.order.OrderList;
import casadamoeda.seame.util.ExtractLines;

import java.util.ArrayList;

public abstract class OperatorList {
    protected final ArrayList<ListItem> items = new ArrayList<>();

    public void GetList(String filename) {
        ExtractLines items = new ExtractLines(filename);
        this.items.addAll(items.GetExtractedLines());
    }

    public ArrayList<ListItem> GetItemList() {
        return this.items;
    }

    public ListItem GetHeader() {
        if (!this.items.isEmpty()) {
            return this.items.get(0);
        } else {
            return null;
        }
    }

    public void PrintItemList() {
        this.items.forEach(System.out::println);
    }

}