package BaekJoon_1654;

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
        long left = 1;
        long right = -1;

        for(int i=0;i<N;i++){
            array[i] = Integer.parseInt(br.readLine());
            if(right<array[i]) right = array[i];
        }

        long mid = (left+right)/2;
        while(left<=right){
            long sum = 0;
            for(int i=0;i<array.length;i++) sum+=array[i]/mid;
            if(sum>=M) {
                left = mid+1;
            }else{
                right = mid-1;
            }
            mid = (left+right)/2;
        }
        System.out.println(mid);
    }
}