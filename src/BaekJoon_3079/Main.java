package BaekJoon_3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] array = new int[N];
        int M = Integer.parseInt(str[1]);

        long min = 1;
        long max = 0;
        for(int i=0;i<N;i++){
            array[i] = Integer.parseInt(br.readLine());
            if(max<array[i]) max = array[i];
        }
        max*=M;
        long mid = (max+min)/2;
        while(min<=max){
            long sum=0;
            for(int i=0;i<N;i++) sum += mid/array[i];
            if(sum>=M){
                max = mid-1;
            }else{
                min = mid+1;
            }
            mid = (min+max)/2;
        }
        System.out.println(mid+1);
    }
}