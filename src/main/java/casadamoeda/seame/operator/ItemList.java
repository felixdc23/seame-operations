package casadamoeda.seame.operator;

import casadamoeda.seame.util.LineExtractor;

import java.util.*;

public class ItemList {
    protected final ArrayList<ListItem> items = new ArrayList<>();

    protected ListItem GetHeader() {
        if (!this.items.isEmpty()) {
            return this.items.get(0);
        } else {
            return null;
        }
    }

    protected ArrayList<String> getItemList() {
        return new ArrayList<>(this.items.stream().map(ListItem::toString).toList());
    }

    protected void addItem(int index, ListItem item) {
        this.items.add(index, item);
    }

    public void loadItemList(String filename) {
        LineExtractor extractor = new LineExtractor(filename);

        ArrayList<String> lines = extractor.getExtractedLines();

        lines = removeDuplicates(lines);

        lines.forEach(s -> this.items.add(new ListItem(s)));
    }

    public ArrayList<String> removeDuplicates(ArrayList<String> list) {
        String header = list.get(0);

        HashSet<String> set = new HashSet<>(list);

        ArrayList<String> tmpList = new ArrayList<>(set.stream().toList());
        Collections.swap(tmpList, tmpList.indexOf(header), 0);

        return tmpList;
    }

}