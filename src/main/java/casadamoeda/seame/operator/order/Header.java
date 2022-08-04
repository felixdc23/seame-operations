package casadamoeda.seame.operator.order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Header {
    private final List<String> headerNames = new ArrayList<>();

    public void AddHeader(String headerLine) {
        this.headerNames.addAll(Arrays.asList(headerLine.split(";")));
    }

    public List<String> GetHeaders() {
        return this.headerNames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!this.headerNames.isEmpty()) {
            sb.append(String.join(";", this.headerNames));
        } else {
            sb.append("Headers not loaded.");
        }

        return sb.toString();    }
}
