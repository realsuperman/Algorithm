package BaekJoon_10989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = new int[10001];
        int index;
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++){
            index = Integer.parseInt(br.readLine());
            array[index] = array[index]+1;
        }

        for(int i=0;i<10001;i++){
            for(int j=0;j<array[i];j++){
                sb.append(i+"\n");
            }
        }
        System.out.println(sb);
    }

}