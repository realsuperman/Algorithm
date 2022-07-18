package BaekJoon_2437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i]= Integer.parseInt(str[i]);

        Arrays.sort(array);
        int sum = array[0];
        if(array[0]>1){ System.out.println(1); return;}

        for(int i=1;i<N;i++){
            if((sum+1)-array[i]>=0) sum+=array[i];
            else break;
        }
        System.out.println(sum+1);
    }
}