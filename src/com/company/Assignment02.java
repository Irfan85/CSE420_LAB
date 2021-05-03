package com.company;

import java.util.Scanner;

public class Assignment02 {
    private static final int START = 0;
    private static final int END_WEB = 6;
    private static final int END_EMAIL = 8;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            String input = sc.nextLine();

            int state = START;
            int lexemePos = 0;
            while (lexemePos < input.length()) {
                char lexeme = input.charAt(lexemePos);

                switch (state){
                    case 0:
                        if(isValidAlphabet(lexeme)){
                            if(lexeme == 'w'){
                                state = 2;
                            }else{
                                state = 1;
                            }
                        }else{
                            //trap
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 1:
                        if(isAlphaNumeric(lexeme)){
                            state = 1;
                        }else if (lexeme == '@'){
                            state = 7;
                        }else {
                            //trap
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 2:
                        if(lexeme == 'w'){
                            state = 3;
                        }else if (isAlphaNumeric(lexeme)){
                            state = 1;
                        }else if (lexeme == '@'){
                            state = 7;
                        }else{
                            //trap;
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 3:
                        if(lexeme == 'w'){
                            state = 4;
                        }else if (isAlphaNumeric(lexeme)){
                            state = 1;
                        }else if (lexeme == '@'){
                            state = 7;
                        }else{
                            //trap;
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 4:
                        if(lexeme == '.'){
                            state = 5;
                        }else if (isAlphaNumeric(lexeme)){
                            state = 1;
                        }else if (lexeme == '@'){
                            state = 7;
                        }else{
                            //trap;
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 5:
                       if(isAlphaNumeric(lexeme)){
                           state = 5;
                       }else if (lexeme == '.'){
                           state = 6;
                       }else{
                           //trap
                           state = 9;
                       }

                       lexemePos++;
                       break;
                    case 6:
                        if(isValidAlphabet(lexeme)){
                            state = 6;
                        }else{
                            //trap
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 7:
                        if(isAlphaNumeric(lexeme)){
                            state = 7;
                        }else if(lexeme == '.'){
                            state = 8;
                        }else{
                            //trap
                            state = 9;
                        }

                        lexemePos++;
                        break;
                    case 8:
                       if(isAlphaNumeric(lexeme)){
                           state = 8;
                       }else{
                           //trap
                           state = 9;
                       }

                       lexemePos++;
                       break;
                    case 9:
                        lexemePos++;
                        break;
                    }
                }

                if(state == END_EMAIL){
                    System.out.println("Email," + i);
                }else if (state == END_WEB){
                    System.out.println("Web," + i);
                }
            }

        }

    private static boolean isAlphaNumeric(char c){
        if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')){
            return true;
        }else {
            return false;
        }
    }

    private static boolean isValidAlphabet(char c){
        if (c >= 'a' && c <= 'z'){
            return true;
        }else {
            return false;
        }
    }
}
