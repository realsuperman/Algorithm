package BaekJoon_1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N==1){System.out.println(0); return;}
        int[] array = new int[N+1];
        array[0]=0;
        for(int i=1;i<=N;i++){
            int min = array[i-1]+1;
            if(i%3==0 && min>array[i/3]+1) min = array[i/3]+1;
            if(i%2==0 && min>array[i/2]+1) min = array[i/2]+1;

            array[i] = min;
        }

        System.out.println(array[N]-1);
    }
}