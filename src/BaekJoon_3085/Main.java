package BaekJoon_3085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int result,N;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] array = new char[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<4;k++){
                    int x = i+dx[k];
                    int y = j+dy[k];
                    if(x<0 || x>=N || y<0 || y>=N) continue;

                    char temp = array[i][j];
                    array[i][j] = array[x][y];
                    array[x][y] = temp;
                    solution(array);
                    temp = array[i][j];
                    array[i][j] = array[x][y];
                    array[x][y] = temp;
                }
            }
        }
        System.out.println(result);
    }

    public static void solution(char[][] array){
        int max = 0;

        for(int i=0;i<N;i++){
            int cnt = 1;
            char ch = array[i][0];

            for(int j=1;j<N;j++){
                if(array[i][j]==ch) cnt++;
                else{
                    if(max<cnt) max=cnt;
                    ch = array[i][j];
                    cnt = 1;
                }
            }
            if(max<cnt) max=cnt;
        }
        if(result<max) result=max;

        max = 0;
        for(int i=0;i<N;i++){
            int cnt = 1;
            char ch = array[0][i];

            for(int j=1;j<N;j++){
                if(array[j][i]==ch) cnt++;
                else{
                    if(max<cnt) max=cnt;
                    ch = array[j][i];
                    cnt = 1;
                }
            }
            if(max<cnt) max=cnt;
        }
        if(result<max) result=max;
    }
}