package BaekJoon_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int findNumber = Integer.parseInt(str[1]);
        int[] array = new int[N+1];
        str = br.readLine().split(" ");
        for(int i=0;i<N;i++) array[i] = Integer.parseInt(str[i]);

        int len = Integer.MAX_VALUE;
        int start=0,end=0,sum=0;

        while(start<=N && end<=N){
            if(sum>=findNumber && len>end-start) len = end - start;
            if(sum < findNumber) sum += array[end++];
            else sum -= array[start++];
        }
        System.out.println(len==Integer.MAX_VALUE?0:len);
    }
}