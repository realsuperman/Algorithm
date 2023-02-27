package BaekJoon_12904.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder from = new StringBuilder(br.readLine());
        StringBuilder to = new StringBuilder(br.readLine());
        while(to.length()>=from.length()){
            if(from.toString().equals(to.toString())) {
                System.out.println(1);
                return;
            }
            if(to.charAt(to.length()-1)=='A'){
                to.deleteCharAt(to.length()-1);
            }
            else{
                to.reverse();
                if(to.charAt(0)!='B') break;
                to.deleteCharAt(0);
            }
        }

        System.out.println(0);
    }
}