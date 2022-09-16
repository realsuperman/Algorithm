package BaekJoon_2292;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 1;
        if(n==1){System.out.println(cnt); return;}

        long sum = 1;
        long value = 6;
        while(true){
            if(n<=sum) {System.out.println(cnt); break;}
            cnt++;
            sum += value;
            value+=6L;
        }

    }
}