package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LexicalAnalysis {
    private static String[] keywords = {"int", "float", "double", "long", "short", "byte", "char", "struct", "typedef", "union", "if", "else"};
    private static String[] mathematicalOperators = {"+", "-", "*", "/", "=", "%"};
    private static String[] logicalOperators = {"<", ">", "!"};

    public static void main(String[] args) {
        Set<String> foundKeywords = new HashSet<>();
        Set<String> foundIdentifiers = new HashSet<>();
        Set<String> foundMathOperators = new HashSet<>();
        Set<String> foundLogicOperators = new HashSet<>();
        Set<String> foundNumericalValues = new HashSet<>();
        Set<String> foundOtherSymbols = new HashSet<>();

        File file = new File("input.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] spaceSplit = line.split("\\s+");
                for (String s : spaceSplit) {
                    String[] tmp = s.split("[,;]");
                    if (isNumerical(tmp[0])) {
                        foundNumericalValues.add(tmp[0]);
                    }
                }

                String[] nonWordSplit = line.split("\\W+");


                for (String s : nonWordSplit) {
                    if (!s.equals("")) {
                        if (isKeyword(s)) {
                            foundKeywords.add(s);
                        } else if (!isNumerical(s)) {
                            foundIdentifiers.add(s);
                        }
                    }
                }

                String[] wordSplit = line.split("\\w");
                ArrayList<String> singleCharSymbols = new ArrayList<>();
                for(String e: wordSplit){
                    for(char c : e.toCharArray()){
                        singleCharSymbols.add(c + "");
                    }
                }

                for(String s : singleCharSymbols){
                    s = s.trim();

                    if(!s.equals("") && !s.equals(".")){
                        if(isMathOperator(s)){
                            foundMathOperators.add(s);
                        }else if (isLogicalOperator(s)){
                            foundLogicOperators.add(s);
                        }else{
                            foundOtherSymbols.add(s);
                        }
                    }
                }
            }

            System.out.print("Keywords: ");
            printSet(foundKeywords);

            System.out.print("Identifiers: ");
            printSet(foundIdentifiers);

            System.out.print("Math Operators: ");
            printSet(foundMathOperators);

            System.out.print("Logical Operators: ");
            printSet(foundLogicOperators);

            System.out.print("Numerical Values: ");
            printSet(foundNumericalValues);

            System.out.print("Others: ");
            printSetForSymbols(foundOtherSymbols);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    private static boolean isKeyword(String s) {
        for (String e : keywords) {
            if (e.equals(s)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isMathOperator(String s) {
        for (String e : mathematicalOperators) {
            if (e.equals(s)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isLogicalOperator(String s) {
        for (String e : logicalOperators) {
            if (e.equals(s)) {
                return true;
            }
        }

        return false;
    }

    private static void printSet(Set<String> set){
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            System.out.print(it.next());

            if(it.hasNext()){
                System.out.print(", ");
            }else{
                System.out.println();
            }
        }
    }

    private static void printSetForSymbols(Set<String> set){
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            System.out.print(it.next());

            if(!it.hasNext()){
                System.out.println();
            }
        }
    }
}
