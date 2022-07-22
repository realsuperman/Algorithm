package BaekJoon_9009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] fibo = new int[500];
        fibo[0]=0; fibo[1]=1;
        for(int i=2;i<500;i++) fibo[i] = fibo[i-1]+fibo[i-2];

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++){
            int number = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();

            int sum = number;
            while(sum>0){
                for(int j=0;j<500;j++){
                    if(fibo[j]>number) {
                        list.add(fibo[j-1]);
                        sum-=fibo[j-1];
                        number -=fibo[j-1];
                        break;
                    }
                }
            }
            for(int j=list.size()-1;j>=0;j--) sb.append(list.get(j)+" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}