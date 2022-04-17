package BaekJoon_2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[Integer.parseInt(br.readLine())];
        String[] str = br.readLine().split(" ");
        long left=1;
        long right=0;
        for(int i=0;i<str.length;i++){
            array[i] = Integer.parseInt(str[i]);
            if(right<array[i]) right = array[i];
        }
        long max = Long.parseLong(br.readLine());
        long mid = (left+right)/2;
        while(left<=right){
            long sum=0;
            for(int i=0;i<array.length;i++){
                if(mid>array[i]) sum+=array[i];
                else sum+=mid;
            }
            if(sum>max) right=mid-1;
            else left=mid+1;
            mid = (left+right)/2;
        }
        System.out.println(mid);
    }
}