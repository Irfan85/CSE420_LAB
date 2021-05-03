package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment03 {
    public static void main(String[] args) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> matchStatus = new ArrayList<>();

        File file = new File("input2.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int expressionCount = Integer.parseInt(reader.readLine());

            for (int i = 0; i < expressionCount; i++) {
                patterns.add(Pattern.compile(reader.readLine()));
            }

            int stringCount = Integer.parseInt(reader.readLine());

            for (int i = 0; i < stringCount; i++) {
                strings.add(reader.readLine());
                matchStatus.add(-1);
            }

            for (int i = 0; i < strings.size(); i++) {
                for (int j = 0; j < patterns.size(); j++) {
                    Matcher matcher = patterns.get(j).matcher(strings.get(i));
                    if (matcher.find()) {
                        matchStatus.set(i, j + 1);
                        break;
                    }
                }
            }

            for (Integer i : matchStatus) {
                switch (i) {
                    case -1:
                        System.out.println("NO, 0");
                        break;
                    default:
                        System.out.println("YES, " + i);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
