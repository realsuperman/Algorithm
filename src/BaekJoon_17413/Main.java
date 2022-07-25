package BaekJoon_17413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder result = new StringBuilder();

        StringBuilder temp = new StringBuilder();

        boolean check = false;
        for(int i=0;i<str.length();i++){
            if(check){
                result.append(str.charAt(i));
                if(str.charAt(i)=='>') check=false;
            }else {
                if (str.charAt(i) == '<') {
                    if (temp.length() > 0) result.append(temp.reverse());
                    temp = new StringBuilder();
                    check = true;
                    result.append(str.charAt(i));
                }else if (str.charAt(i) == ' ') {
                    if (temp.length() > 0) result.append(temp.reverse());
                    temp = new StringBuilder();
                    result.append(str.charAt(i));
                } else {
                    temp.append(str.charAt(i));
                }
            }
        }
        if (temp.length() > 0) result.append(temp.reverse());
        System.out.println(result);
    }
}