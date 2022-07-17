package BaekJoon_13164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str =br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        int[] array  = new int[N];
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<N-1;i++) list.add(array[i+1]-array[i]);
        Collections.sort(list);
        int sum = 0;
        for(int i=0;i<N-K;i++) sum+=list.get(i);
        System.out.println(sum);
    }
}