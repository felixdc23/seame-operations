package casadamoeda.seame.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListItem {
    protected final List<String> itemDetails = new ArrayList<>();
    public ListItem(String line) {
        LoadDetails(line);
    }

    public List<String> GetItems() {
        return this.itemDetails;
    }

    protected void LoadDetails(String line) {
        try {
            this.itemDetails.addAll(Arrays.asList(line.split(";")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!this.itemDetails.isEmpty()) {
            sb.append(String.join(";", this.itemDetails));
        } else {
            sb.append("Order not loaded.");
        }

        return sb.toString();
    }
}
