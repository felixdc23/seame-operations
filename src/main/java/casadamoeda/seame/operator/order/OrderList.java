package casadamoeda.seame.operator.order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private Header header = new Header();
    private List<Order> orders = new ArrayList<>();

    private void AddOrder(Order order) {
        this.orders.add(order);
    }

    protected void ExtractLines(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            String line = br.readLine();

            while (line != null) {
                AddOrder(new Order(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lines extracted.");
    }

    protected List<Order> GetOrderList() {
        return this.orders;
    }

    public String GetHeader() {
        if (!this.orders.isEmpty()) {
            return this.orders.get(0).toString();
        } else {
            return "Orders not loaded.";
        }
    }
}