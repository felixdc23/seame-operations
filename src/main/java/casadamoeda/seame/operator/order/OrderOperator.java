package casadamoeda.seame.operator.order;

import casadamoeda.seame.operator.Operator;

public class OrderOperator extends Operator {
    private final OrderList orders = new OrderList();

    public OrderOperator(String filename) {
        super(filename);
    }

    public void LoadOrders() {
        this.orders.ExtractLines(this.filename);
    }

    public void PrintOrderList() {
        this.orders.GetOrderList().forEach(System.out::println);
    }

    public String GetHeader() {
        return this.orders.GetHeader();
    }

}
