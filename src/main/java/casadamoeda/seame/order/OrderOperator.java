package casadamoeda.seame.order;

import casadamoeda.seame.util.operator.ListItem;
import casadamoeda.seame.util.operator.Operator;

import java.util.ArrayList;

public class OrderOperator extends Operator {
    private final OrderList orders = new OrderList();

    public OrderOperator(String filename) {
        super(filename);
    }

    public void LoadOrders() {
        this.orders.GetList(this.filename);
    }

    @Override
    public ArrayList<ListItem> GetList() {
        return this.orders.GetItemList();
    }

}
