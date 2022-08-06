package casadamoeda.seame.util.operator;

import casadamoeda.seame.order.OrderList;

import java.util.ArrayList;

public abstract class Operator {
    protected String filename;
    public Operator(String filename) {
        this.filename = filename;
    }

    public abstract ArrayList<ListItem> GetList();

}
