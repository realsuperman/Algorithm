package BaekJoon_15552;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String[] str;
        int sum;

        for(int i=0;i<n;i++){
            str = br.readLine().split(" ");
            sum = Integer.parseInt(str[0])+Integer.parseInt(str[1]);
            sb.append(sum+"\n");
        }
        System.out.print(sb);
    }
}
