package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment04 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(public|private|protected)?((\\s)+static)?(\\s)+[a-zA-Z]\\w*(\\s)+[a-zA-Z]\\w*\\(.*\\)");
        Pattern methodName = Pattern.compile("[a-zA-Z]\\w*\\(.*\\)");

        File file = new File("input3.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            System.out.println("Methods:");

            while ((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line.trim());

                if(matcher.matches()){
                    Matcher methodNameMatcher = methodName.matcher(line);

                    if(methodNameMatcher.find()){
                        String[] temp = line.substring(0, methodNameMatcher.start()).split(" ");
                        System.out.println(line.substring(methodNameMatcher.start()) + ", return type: " + temp[temp.length - 1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
