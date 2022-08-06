package casadamoeda.seame.util;

import casadamoeda.seame.util.operator.ListItem;
import casadamoeda.seame.util.operator.OperatorList;

import java.util.ArrayList;
import java.util.Arrays;

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

        protected void SelectHeaders(Integer[] intList) {
            this.selectedColumns.addAll(Arrays.stream(intList).toList());
        }

        protected ArrayList<ListItem> GetRows() {
            return this.table;
        }

        public void PrintTable() {
            System.out.println("Headers:");
            this.headers.forEach(s -> System.out.println(s.indexOf(s) + " " + s));

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

    public void PrintTableA() {
        this.leftTable.PrintTable();
    }

    public void PrintTableB() {
        this.rightTable.PrintTable();
    }

    public void Merge(int colA, int colB) {
        ArrayList<String> merged = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        sb.append(this.leftTable.headers.get(colA)).append("-").append(this.leftTable.headers.get(colB));
        sb.append(String.join(";", this.leftTable.headers)).append(";");
        sb.append(String.join(";", this.rightTable.headers));
        merged.add(sb.toString());

        this.leftTable.table.forEach(itemA -> {
            this.rightTable.table.forEach(itemB -> {
                if (itemA.GetItems().get(colA).equals(itemB.GetItems().get(colB))) {
                    sb.setLength(0);
                    sb.append(itemA.GetItems().get(colA));
                    leftTable.selectedColumns.forEach(col -> {
                        sb.append(";").append(itemA.GetItems().get(col));
                    });
                    rightTable.selectedColumns.forEach(col -> {
                        sb.append(";").append(itemA.GetItems().get(col));
                    });
                }
            });
        });
        merged.add(sb.toString());
    }

}
