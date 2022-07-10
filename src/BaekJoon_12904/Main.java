package BaekJoon_12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String from = br.readLine();
        StringBuilder to = new StringBuilder(br.readLine());

        while(true){
            if(to.length()<from.length()) break;
            if(to.toString().equals(from)){System.out.println(1); return;}

            char ch = to.substring(to.length()-1).charAt(0);
            if(ch=='A'){
                to.deleteCharAt(to.length()-1);
            }else if(ch=='B'){
                to.deleteCharAt(to.length()-1);
                to.reverse();
            }else{
                break;
            }
        }
        System.out.println(0);
    }
}