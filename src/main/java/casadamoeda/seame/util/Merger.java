package casadamoeda.seame.util;

import casadamoeda.seame.operator.ListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Merger {
    Table leftTable;
    Table rightTable;

    private static class Table {
        private final ArrayList<String> headers = new ArrayList<>();
        private final ArrayList<Integer> selectedColumns = new ArrayList<>();
        private final ArrayList<ListItem> table;

        public Table(ArrayList<ListItem> table) {
            this.table = table;
            LoadHeader();
        }

        protected void LoadHeader() {
            this.headers.addAll(Arrays.stream(this.table.get(0).toString().split(";")).toList());
        }

        protected void AddSelectedHeaders(Integer[] intList) {
            this.selectedColumns.addAll(Arrays.stream(intList).toList());
        }

        public void PrintTable() {
            System.out.println("Headers:");

            final AtomicInteger count = new AtomicInteger();
            this.headers.forEach(s -> System.out.println(count.incrementAndGet() - 1 + " - " + s.replace("\"", "")));

            System.out.println("Rows:");
            this.table.forEach(System.out::println);

            System.out.println("Selected columns:");
            this.selectedColumns.forEach(System.out::println);
        }
    }

    public Merger(ArrayList<ListItem> table1, ArrayList<ListItem> table2) {
        this.leftTable = new Table(table1);

        this.rightTable = new Table(table2);
    }

    public void PrintTable(String table) {
        if (table.equalsIgnoreCase("A")) {
            this.leftTable.PrintTable();
        } else if (table.equalsIgnoreCase("B")) {
            this.rightTable.PrintTable();
        } else {
            System.out.println("Invalid table");
        }
    }

    public void SelectHeaders(String table, Integer[] headers) {
        if (table.equalsIgnoreCase("A")) {
            this.leftTable.AddSelectedHeaders(headers);
        } else if (table.equalsIgnoreCase("B")) {
            this.rightTable.AddSelectedHeaders(headers);
        } else {
            System.out.println("Invalid table");
        }
    }

    public void Merge(int colA, int colB) {
        ArrayList<String> merged = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

//        sb.append(this.leftTable.headers.get(colA)).append("-").append(this.leftTable.headers.get(colB));
        leftTable.selectedColumns.forEach(s -> sb.append(leftTable.headers.get(s)).append(";"));
        rightTable.selectedColumns.forEach(s -> sb.append(rightTable.headers.get(s)).append(";"));

        merged.add(sb.toString());

        this.leftTable.table.stream().skip(1).forEach(itemA -> this.rightTable.table.stream().skip(1).forEach(itemB -> {
            sb.setLength(0);

            if (itemA.GetItems().get(colA).replace("\"", "").equals(itemB.GetItems().get(colB).replace("\"", "").substring(11, 17))) {
//                    sb.append(itemA.GetItems().get(colA));
                leftTable.selectedColumns.forEach(col -> sb.append(itemA.GetItems().get(col)).append(";"));
                rightTable.selectedColumns.forEach(col -> sb.append(itemB.GetItems().get(col)).append(";"));
                merged.add(sb.toString());
            }
        }));

        merged.forEach(System.out::println);
    }

}
