package BaekJoon_2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] array = new int[N];
        for(int i=0;i<N;i++) array[i]= Integer.parseInt(str[i]);

        int findNumber = 0;
        int cnt = Integer.MAX_VALUE ,start = 0,end =N-1;
        List<Integer> list = new ArrayList<>(2);
        while(start < end) {
            int sum = array[start]+array[end];
            if(Math.abs(cnt)>Math.abs(sum)){
                list = new ArrayList<>(2);
                list.add(array[start]);
                list.add(array[end]);
                cnt = sum;
            }


            if(sum <= findNumber) {
                start++;
            }
            else {
                end--;
            }
        }
        System.out.println(list.get(0)+" "+list.get(1));
    }
}