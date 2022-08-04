package casadamoeda.seame.operator.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private final List<String> orderDetails = new ArrayList<>();

   public Order(String line) {
        LoadDetails(line);
    }

    protected void LoadDetails(String line) {
        try {
            this.orderDetails.addAll(Arrays.asList(line.split(";")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> GetOrder() {
        if (!this.orderDetails.isEmpty()) {
            return this.orderDetails;
        } else {
            System.out.println("Order not loaded.");
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!this.orderDetails.isEmpty()) {
            sb.append(String.join(";", this.orderDetails));
        } else {
            sb.append("Order not loaded.");
        }

        return sb.toString();
    }
}