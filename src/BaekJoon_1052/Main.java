package BaekJoon_1052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        if(N==K){System.out.println(0); return;}

        int cnt = 0;
        while(true){
            int tmp = N + cnt;
            int count = 0;

            while(tmp > 0) {
                if(tmp % 2 != 0) count++;
                tmp /= 2;
            }

            if(count <= K){System.out.println(cnt); return;}
            cnt++;
        }
    }
}