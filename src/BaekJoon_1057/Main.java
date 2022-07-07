package BaekJoon_1057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int from = Integer.parseInt(str[1]);
        int to = Integer.parseInt(str[2]);

        int cnt = 1;
        while(true){
            int num1 = (int) Math.ceil(from/2.0);
            int num2 = (int) Math.ceil(to/2.0);
            if(num1==num2) break;
            cnt++;

            from = num1;
            to = num2;
        }
        System.out.println(cnt);
    }
}