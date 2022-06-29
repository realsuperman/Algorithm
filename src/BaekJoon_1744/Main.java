package BaekJoon_1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for(int i=0;i<N;i++) array[i] = Integer.parseInt(br.readLine());
        Arrays.sort(array);

        int sum = 0;
        int number = N%2;
        for(int i=0;i<N;){
            if(array[i]<0){
                if(i+1<N  && array[i+1]<=0){ sum = sum+(array[i]*array[i+1]); i+=2;}
                else{ sum+=array[i]; i++;}
            }
            else if(array[i]<=1){
                sum+=array[i];
                i++;
            }
            else{
                if(i%2==number && i+1<N){ sum = sum+(array[i]*array[i+1]); i+=2;}
                else{ sum+=array[i]; i++;}
            }
        }
        System.out.println(sum);
    }
}