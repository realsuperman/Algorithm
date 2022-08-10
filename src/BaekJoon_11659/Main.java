package BaekJoon_11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[] array = new int[N];
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i]= Integer.parseInt(str[i]);
        int[] dp = new int[N]; dp[0]=array[0];
        for(int i=1;i<N;i++) dp[i] = dp[i-1]+array[i];

        while(M-->0){
            str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0]);
            int to = Integer.parseInt(str[1]);
            if(from==to) System.out.println(array[from-1]);
            else{
                if(from==1) System.out.println(dp[to-1]);
                else{
                    int v = dp[to-1];
                    v-=dp[from-2];
                    System.out.println(v);
                }
            }
        }
    }
}