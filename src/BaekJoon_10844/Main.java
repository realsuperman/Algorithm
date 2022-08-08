package BaekJoon_10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] array = new long[10][N+1];
        array[0][1] = 0;
        for(int i=1;i<=9;i++) array[i][1]=1;

        for(int i=2;i<=N;i++){
            for(int j=0;j<=9;j++){
                if(j==0) {
                    array[j][i] = array[j+1][i-1]%1000000000;
                }else if(j==9){
                    array[j][i]=array[j-1][i-1]%1000000000;
                }else{
                    array[j][i]=(array[j-1][i-1]%1000000000)+(array[j+1][i-1]%1000000000);
                }
            }
        }

        long sum = 0;
        for(int i=0;i<=9;i++) sum+=array[i][N];
        System.out.println(sum%1000000000);
    }
}