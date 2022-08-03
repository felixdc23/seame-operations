package br.gov.casadamoeda.seame.bankslips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    private String csvLine;

    public Converter() {
        this.csvLine = "";
    }

    private Boolean CheckNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private String ApplyCsvFormat(String line) {
        String formatLine;

        if (CheckNumeric(line)) {
            formatLine = line;
        } else {
            formatLine = "\"" + line + "\"";
        }

        return formatLine;
    }

    private String ConvertToCsv(String line) {

        String[] tmpLine = {"0", "", line};

        return ConvertStringToCsv(tmpLine);
    }

    private String ConvertStringToCsv(String[] line) {
        if (line[2].length() > 0) {
            StringBuilder sb = new StringBuilder(line[1]);

            List<String> tmpLine = new ArrayList<>(Arrays.stream(line[2].split(" ")).toList());
            String[] tmpArray = {"", "", ""};

            if (line[0].equals("0")) {
                tmpArray[1] = ApplyCsvFormat(tmpLine.remove(0));
                tmpArray[2] = String.join(" ", tmpLine);
                tmpArray[0] = String.valueOf(Integer.parseInt(line[0]) + 1);
                ConvertStringToCsv(tmpArray);
            } else {
                if (line[0].equals("1")) {
                    String name = tmpLine.stream().takeWhile(s -> !CheckNumeric(s.substring(0, 1)))
                            .collect(Collectors.joining(" "));

                    sb.append(";");
                    sb.append(ApplyCsvFormat(name));

                    tmpArray[1] = sb.toString();
                    tmpArray[2] = line[2].substring(name.length() + 1);
                    for (String item : tmpLine) {
                        if (CheckNumeric(item.substring(0, 1))) {
                            break;
                        } else {
                            tmpArray[0] = String.valueOf(Integer.parseInt(line[0]) + 1);
                        }
                    }

                } else {
                    sb.append(";");
                    tmpArray[0] = String.valueOf(Integer.parseInt(line[0]) + 1);
                    String insertValue = tmpLine.remove(0);

                    if (insertValue.contains(",")
                            && sb.toString().chars().filter(ch -> ch == '/').count() < 3
                            && !sb.toString().contains(";;")) {
                        sb.append(";");
                    }
                    sb.append(ApplyCsvFormat(insertValue));

                    tmpArray[1] = sb.toString();
                    tmpArray[2] = String.join(" ", tmpLine);
                    if (tmpArray[2].isBlank()) {
                        this.csvLine = tmpArray[1];
                    }
                }
                ConvertStringToCsv(tmpArray);
            }

        }

        return this.csvLine;
    }

    public String GetCsv(String line) {
        return ConvertToCsv(line);
    }
}
