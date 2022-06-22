package BaekJoon_1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        Integer[] b = new Integer[N];

        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) a[i]= Integer.parseInt(str[i]);
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) b[i] = Integer.parseInt(str[i]);
        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        int sum = 0;
        for(int i=0;i<N;i++){
            sum+=a[i]*b[i];
        }
        System.out.println(sum);
    }
}