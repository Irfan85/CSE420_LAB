package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SimpleLexicalAnalysis {
    private static String[] keywordArray = {"int", "float", "if", "else"};
    private static String[] mathematicalOperatorArray = {"+", "-", "*", "/", "="};
    private static String[] logicalOperatorArray = {"<", ">"};

    public static void main(String[] args) {
        Set<String> keywords = new HashSet<>();
        Set<String> identifiers = new HashSet<>();
        Set<String> mathematicalOperators = new HashSet<>();
        Set<String> logicalOperators = new HashSet<>();
        Set<String> numericalValues = new HashSet<>();
        Set<String> others = new HashSet<>();

        File file = new File("input.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                String[] splitA = line.split("\\s+");
                String[] splitB = line.split("\\w");
                String[] splitC = line.split("\\W+");

                for (String splitA_element : splitA) {
                    String[] tempSplit = splitA_element.split(";");
                    String[] tempSplit2 = tempSplit[0].split(",");
                    if (isNumerical(tempSplit2[0])) {
                        numericalValues.add(tempSplit2[0]);
                    }
                }

                for (String splitB_element : splitB) {
                    String temp = splitB_element.trim();

                    if (!temp.equals("") && !temp.equals(".")) {
                        if (match(temp, mathematicalOperatorArray)) {
                            mathematicalOperators.add(temp);
                        } else if (match(temp, logicalOperatorArray)) {
                            logicalOperators.add(temp);
                        } else
                            others.add(temp);
                    }
                }

                for (String splitC_element : splitC) {
                    if (!splitC_element.equals("")) {
                        if (match(splitC_element, keywordArray)) {
                            keywords.add(splitC_element);
                        } else if (!isNumerical(splitC_element)) {
                            identifiers.add(splitC_element);
                        }
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        System.out.print("Keywords: ");
        String[] keywords_array = new String[keywords.size()];
        keywords.toArray(keywords_array);

        for (int i = 0; i < keywords_array.length; i++) {
            if (i != (keywords_array.length - 1)) {
                System.out.print(keywords_array[i] + ", ");
            } else {
                System.out.println(keywords_array[i]);
            }
        }

        System.out.print("Identifiers: ");
        String[] identifiers_array = new String[identifiers.size()];
        identifiers.toArray(identifiers_array);

        for (int i = 0; i < identifiers_array.length; i++) {
            if (i != (identifiers_array.length - 1)) {
                System.out.print(identifiers_array[i] + ", ");
            } else {
                System.out.println(identifiers_array[i]);
            }
        }

        System.out.print("Math Operators: ");
        String[] mathematicalOperators_array = new String[mathematicalOperators.size()];
        mathematicalOperators.toArray(mathematicalOperators_array);

        for (int i = 0; i < mathematicalOperators_array.length; i++) {
            if (i != (mathematicalOperators_array.length - 1)) {
                System.out.print(mathematicalOperators_array[i] + ", ");
            } else {
                System.out.println(mathematicalOperators_array[i]);
            }
        }

        System.out.print("Logical Operators: ");
        String[] logicalOperators_array = new String[logicalOperators.size()];
        logicalOperators.toArray(logicalOperators_array);

        for (int i = 0; i < logicalOperators_array.length; i++) {
            if (i != (logicalOperators_array.length - 1)) {
                System.out.print(logicalOperators_array[i] + ", ");
            } else {
                System.out.println(logicalOperators_array[i]);
            }
        }

        System.out.print("Numerical Values: ");
        String[] numericalValues_array = new String[numericalValues.size()];
        numericalValues.toArray(numericalValues_array);

        for (int i = 0; i < numericalValues_array.length; i++) {
            if (i != (numericalValues_array.length - 1)) {
                System.out.print(numericalValues_array[i] + ", ");
            } else {
                System.out.println(numericalValues_array[i]);
            }
        }

        System.out.print("Others: ");
        String[] others_array = new String[others.size()];
        others.toArray(others_array);

        for (int i = 0; i < others_array.length; i++) {
            System.out.print(others_array[i] + " ");
        }
    }

    private static boolean isNumerical(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean match(String string, String[] array) {
        for (String element : array) {
            if (element.equals(string)) {
                return true;
            }
        }
        return false;
    }
}
