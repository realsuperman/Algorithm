package BaekJoon_18111.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    static int N,M,B;
    static int[][] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        B = Integer.parseInt(str[2]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        int v = array[0][0];

        int time = Integer.MAX_VALUE;
        int groundHeight = -1;
        for(int value=0;value<=256;value++){
            int temp = B;
            int eTime = 0;
            for(int i=0;i<N;i++){ // 블록제거
                for(int j=0;j<M;j++){
                    if(array[i][j]>value){
                        temp+=array[i][j]-value;
                        eTime= eTime+(2* (array[i][j]-value));
                    }
                }
            }

            boolean check = false;
            for(int i=0;i<N;i++){ // 블록삽입
                for(int j=0;j<M;j++){
                    if(array[i][j]<value){
                        if(temp >= value-array[i][j]) {
                            temp -= value-array[i][j];
                            eTime+=value-array[i][j];
                        }else{
                            check = true;
                        }
                    }
                }
            }
            if(check) continue;
            if( eTime<=time){
                time = eTime;
                groundHeight = value;
            }
        }

        System.out.println(time+" "+groundHeight);
    }
}