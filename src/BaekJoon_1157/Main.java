package BaekJoon_1157;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.toLowerCase();
        int[] array = new int[26];
        boolean sw = false;
        int max = 0;
        int index = 0;

        for(int i=0;i<str.length();i++) {
            index = str.charAt(i) - 97;
            array[index] = array[index] + 1;
        }

        for(int i=0;i<array.length;i++){
            if(max<array[i]){
                max = array[i];
                sw = false;
                index = i;
            }else if(max==array[i]){
                sw = true;
            }
        }

        if(sw) System.out.print("?");
        else System.out.print((char)(index+65));
    }
}