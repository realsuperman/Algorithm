package BaekJoon_10610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if(!str.contains("0")){System.out.println(-1); return;}
        int sum = 0;
        for(int i=0;i<str.length();i++) sum+=str.charAt(i)-48;
        if(sum%3!=0) {System.out.println(-1); return;}
        String[] result = new String[str.length()];
        for(int i=0;i<str.length();i++) result[i] = String.valueOf(str.charAt(i));
        Arrays.sort(result, Collections.reverseOrder());
        for(int i=0;i<result.length;i++) System.out.print(result[i]);
    }
}