package BaekJoon_2302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        dp[0] = 1; dp[1] = 1;
        for(int i=2;i<=N;i++) dp[i]=dp[i-1]+dp[i-2];

        int M = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<M;i++) list.add(Integer.valueOf(br.readLine()));

        int result = 1;
        int seat = 0;
        for(int i=0;i<list.size();i++){
            result *= dp[list.get(i) - seat  - 1];
            seat = list.get(i);
        }
        result*=dp[N-seat];
        System.out.println(result);
    }
}