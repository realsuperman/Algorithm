package BaekJoon_1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] array = new int[41][2];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array[0][0] = 1;
        array[1][1] = 1;

        for(int i=2;i<41;i++){
            array[i][0] = array[i-2][0]+array[i-1][0];
            array[i][1] = array[i-2][1]+array[i-1][1];
        }

        int cnt = Integer.parseInt(br.readLine());
        int index;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<cnt;i++){
            index = Integer.parseInt(br.readLine());
            sb.append(array[index][0] + " " + array[index][1]).append('\n');
        }
        System.out.print(sb);
    }
}