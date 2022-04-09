package BaekJoon_1484;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] array = new int[100000];
        boolean sw = false;
        int i,start=0,end=0;
        for(i=0;i<100000;i++) array[i]=i+1;

        while(!(start==n-1 && end==n-1)){
            if(array[end]*array[end]-array[start]*array[start]==n){
                sw = true;
                sb.append((end+1)+"\n");
                end++;
            }else if(array[end]*array[end]-array[start]*array[start]<n){
                end++;
            }else{
                start++;
            }
        }

        if(sw) System.out.print(sb);
        else System.out.print(-1);
    }
}