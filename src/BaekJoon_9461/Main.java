package BaekJoon_9461;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] array = new long[100];
        StringBuilder sb =new StringBuilder();
        array[0] = 1; array[1] = 1; array[2] = 1;

        for(int i=3;i<100;i++){
            array[i] = array[i-3]+array[i-2];
        }
        for(int i=0;i<n;i++){
            sb.append(array[Integer.parseInt(br.readLine())-1]+"\n");
        }
        System.out.print(sb);
    }

}
