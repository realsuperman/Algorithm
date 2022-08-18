package BaekJoon_18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M,B,cnt = Integer.MAX_VALUE,height;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        B = Integer.parseInt(str[2]);
        array = new int[N][M];

        int from = Integer.MAX_VALUE; // 최소
        int to = Integer.MIN_VALUE; // 최대
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
                if(from>array[i][j]) from=array[i][j];
                if(to<array[i][j]) to=array[i][j];
            }
        }
        solution(from,to);
        System.out.println(cnt+" "+height);
    }

    public static void solution(int from,int to){
        while(from<=to){
            int opCnt = 0;
            int blockCnt = B;
            boolean check = false;

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(array[i][j]>from){
                        int v = array[i][j]-from;
                        opCnt+=2*v;
                        blockCnt+=v;
                    }
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(array[i][j]<from){
                        int v = from-array[i][j];
                        if(v>blockCnt){ check=true; break;}
                        opCnt+=v;
                        blockCnt-=v;
                    }
                }
            }
            if(check){ from++; continue;}
            if(cnt>=opCnt){
                cnt = opCnt;
                height=from;
            }
            from++;
        }

    }
}