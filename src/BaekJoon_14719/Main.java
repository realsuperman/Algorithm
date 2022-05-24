package BaekJoon_14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int value;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][M];
        str = br.readLine().split(" ");

        int v = 0;
        while(v<M){
            int depth = N-1;
            for(int i=0;i<Integer.parseInt(str[v]);i++){
                array[depth--][v] = 1;
            }
            v++;
        }
        for(int i=N-1;i>=0;i--){
            for(int j=1;j<M;j++){
                if(array[i][j]==0) solution(i,j,N,M,array);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==2) value++;
            }
        }
        System.out.println(value);
    }
    public static void solution(int x,int y,int N,int M,int[][] array){
        boolean chk = false;
        if(array[x][y-1]==1){
            int i = y+1;
            for(;i<M;i++){
                if(array[x][i]==1){
                    chk=true;
                    break;
                }
            }
            if(chk){
                for(i=i-1;i>=y;i--) array[x][i]=2;
            }
        }
    }
}
