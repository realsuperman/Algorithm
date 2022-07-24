package BaekJoon_18310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N+1];

        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i]= Integer.parseInt(str[i]);
        Arrays.sort(array);

        int result = 0;
        if(N%2==0){
            result = array[N/2];
        }else{
            result = array[(int)Math.ceil((double)N/2)];
        }
        System.out.println(result);
    }
}