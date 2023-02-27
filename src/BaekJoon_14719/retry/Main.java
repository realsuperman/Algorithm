package BaekJoon_14719.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    static int N,M;
    static int[][] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        str = br.readLine().split(" ");
        int depth = 0;
        for(String s : str){
            int index = N-1;
            int v = Integer.parseInt(s);
            while(v>0){
                array[index--][depth] = 1;
                v--;
            }
            depth++;
        }

        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==0){
                    if(isWall(i,j)) result++;
                }
            }
        }
        System.out.println(result);
    }

    public static boolean isWall(int x, int y){
        int value = 0;
        for(int index=y-1;index>=0;index--) if(array[x][index]==1){ value++; break;}
        for(int index=y+1;index<M;index++) if(array[x][index]==1){ value++; break;}

        return value==2?true:false;
    }
}