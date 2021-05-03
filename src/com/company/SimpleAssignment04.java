package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleAssignment04 {
    public static void main(String[] args) {
        Pattern pattern1 = Pattern.compile("(public|private|protected)(\\s(static))* \\w+ \\w+\\(.*\\)");
        Pattern pattern2 = Pattern.compile("\\w+\\(.*\\)");
        Pattern pattern3 = Pattern.compile("(public|private|protected)(\\s(static))*");

        File file = new File("input3.txt");
        System.out.println("Methods:");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null){
                Matcher matcher = pattern1.matcher(line);
                if(matcher.matches()){
                    Matcher matcher2 = pattern2.matcher(line);
                    Matcher matcher3 = pattern3.matcher(line);
                    if(matcher2.find() && matcher3.find()){
                        String return_type= line.substring(matcher3.end(), matcher2.start()).trim();
                        System.out.println(line.substring(matcher2.start()) + ", return type: " + return_type);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
