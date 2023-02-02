package BaekJoon_3085.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    static int N;
    static char[][] array;
    static int MAX = Integer.MIN_VALUE;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new char[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<N;j++) array[i][j]= str[j].charAt(0);
        }

        solution();
    }

    private static void solution(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<4;k++){
                    int x = dx[k]+i;
                    int y = dy[k]+j;
                    if(x<0 || x>=N || y<0 || y>=N || array[i][j]==array[x][y]) continue;
                    swap(i,j,x,y);
                    getMaxValue();
                    swap(i,j,x,y);
                }
            }
        }

        System.out.println(MAX);
    }

    private static void swap(int x1, int y1, int x2, int y2) {
        char temp = array[x1][y1];
        array[x1][y1]=array[x2][y2];
        array[x2][y2]=temp;
    }

    private static int getMaxValue(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                char ch = array[i][j];
                int cnt = 1;
                for(int index=i+1;index<N;index++){ if(array[index][j]!=ch) break; cnt++;}
                MAX = cnt>MAX?cnt:MAX;
                cnt = 1;
                for(int index=j+1;index<N;index++){if(array[i][index]!=ch) break; cnt++;}
                MAX = cnt>MAX?cnt:MAX;
            }
        }
        return MAX;
    }

}