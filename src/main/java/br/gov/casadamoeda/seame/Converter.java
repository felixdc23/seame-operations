package br.gov.casadamoeda.seame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Converter {

    private String[] line;
    private String csvLine;

    public Converter() {
        this.csvLine = "";
    }

    private Boolean CheckNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private String ApplyCsvFormat(String line) {
        String formatLine = "";

        if (CheckNumeric(line)) {
            formatLine = line;
        } else {
            formatLine = "\"" + line + "\"";
        }

        return formatLine;
    }

    private String ConvertToCsv(String line) {
        
        String tmpLine[] = {"0", "", line};
        
        return ConvertStringToCsv(tmpLine);
    }

    private String ConvertStringToCsv(String[] line) {
        if (line[2].length() > 0) {
            StringBuilder sb = new StringBuilder(line[1]);

            List<String> tmpLine = new ArrayList<>(Arrays.stream(line[2].split(" ")).toList());
            String[] tmpArray = {"","",""};

            if (line[0].equals("0")) {
                tmpArray[1] = ApplyCsvFormat(tmpLine.remove(0));
                tmpArray[2] = String.join(" ", tmpLine);
                tmpArray[0] = String.valueOf(Integer.parseInt(line[0]) + 1);
                ConvertStringToCsv(tmpArray);
            } else if (line[0].equals("1")) {
                String name = tmpLine.stream().takeWhile(s -> !CheckNumeric(s.substring(0, 1)))
                        .collect(Collectors.joining(" "));

                sb.append(";");
                sb.append(ApplyCsvFormat(name));

                tmpArray[1] = sb.toString();

                Pattern pattern = Pattern.compile("^\\D*(\\d)");
                Matcher matcher = pattern.matcher(line[2]);
                matcher.find();
                tmpArray[2] = line[2].substring(name.length() + 1);
                for (String item : tmpLine) {
                    if (CheckNumeric(item.substring(0, 1))) {
                        break;
                    } else {
                        tmpArray[0] = String.valueOf(Integer.parseInt(line[0]) + 1);
                    }
                }

                ConvertStringToCsv(tmpArray);
            } else {
                sb.append(";");
                tmpArray[0] = String.valueOf(Integer.parseInt(line[0]) + 1);
                sb.append(ApplyCsvFormat(tmpLine.remove(0)));
                tmpArray[1] = sb.toString();
                tmpArray[2] = String.join(" ", tmpLine);
                ConvertStringToCsv(tmpArray);
                if (tmpArray[2].isBlank()) {
                    this.csvLine = tmpArray[1];
                }
            }

        }

        return this.csvLine;
    }

    public String GetCsv(String line) {
        return ConvertToCsv(line);
    }
}
