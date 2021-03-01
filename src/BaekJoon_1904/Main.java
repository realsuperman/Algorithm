package BaekJoon_1904;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] array = new long[n];
        array[0] = 1;
        if(n>1) array[1] = 2;
        for(int i=2;i<n;i++){
            array[i] = (array[i-1]+array[i-2])%15746;
        }
        System.out.print(array[n-1]);
    }

}
