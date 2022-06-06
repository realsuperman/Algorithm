package BaekJoon_3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] array = new int[N];
        for(int i=0;i<N;i++) array[i]= Integer.parseInt(str[i]);
        int findNumber = Integer.parseInt(br.readLine());
        Arrays.parallelSort(array);
        int cnt = 0 ,start = 0,end =N-1;
        while(start < end) {
            int sum = array[start] + array[end];
            if(sum == findNumber) {
                cnt++;
            }

            if(sum <= findNumber) {
                start++;
            }
            else {
                end--;
            }
        }

        System.out.println(cnt);
    }
}