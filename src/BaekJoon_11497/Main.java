package BaekJoon_11497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) list.add(Integer.valueOf(str[j]));
            Collections.sort(list);
            int[] result = new int[N];
            for(int j=0;j<N/2;j++){
                result[j] = list.remove(0);
                result[N-j-1] = list.remove(0);
            }
            if(N%2==1) result[N/2] = list.remove(0);
            int MAX = Integer.MIN_VALUE;
            for(int j=0;j<N-1;j++){
                if(j==0) MAX = MAX<Math.abs(result[0]-result[N-1])?Math.abs(result[0]-result[N-1]):MAX;
                MAX = MAX<Math.abs(result[j]-result[j+1])?Math.abs(result[j]-result[j+1]):MAX;
            }
            sb.append(MAX+"\n");
        }
        System.out.println(sb);
    }
}