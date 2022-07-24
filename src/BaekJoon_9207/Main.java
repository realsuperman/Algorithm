package BaekJoon_9207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dx = {1,-1,0,0}; // up,down,right,left
    static int[] dy = {0,0,1,-1}; // up,down,right,left
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            char[][] array = new char[5][9];
            for(int j=0;j<5;j++){
                String[] str = br.readLine().split("");
                for(int k=0;k<str.length;k++){
                    array[j][k] = str[k].charAt(0);
                }
            }
            result = new int[9];
            for(int j=0;j<9;j++) result[j]=Integer.MAX_VALUE;
            solution(array,0);
            for(int j=0;j<9;j++){
                if(result[j]!=Integer.MAX_VALUE){
                    System.out.println(j+" "+result[j]);
                    break;
                }
            }
            if(i!=T-1) br.readLine();
        }
    }

    public static void solution(char[][] array,int cnt) {
        int number = result[check(array)];
        if(cnt<number) result[check(array)] = cnt;

        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(array[i][j]=='o'){
                    for(int k=0;k<4;k++){
                        int x = dx[k]+i;
                        int y = dy[k]+j;
                        if(x<0 || x>=5 || y<0 || y>=9) continue;

                        int tempX = x;
                        int tempY = y;
                        if(array[x][y]=='o'){
                            x = dx[k]+x;
                            y = dy[k]+y;
                            if(x<0 || x>=5 || y<0 || y>=9 || array[x][y]=='#' || array[x][y]=='o') continue;

                            char[][] temp = new char[5][9];
                            for(int p=0;p<5;p++) for(int q=0;q<9;q++) temp[p][q]=array[p][q];
                            array[x][y]='o';
                            array[tempX][tempY] = '.';
                            array[i][j]='.';

                            solution(array,cnt+1);
                            array = temp;
                        }
                    }
                }
            }
        }

    }

    public static int check(char[][] array){
        int cnt = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(array[i][j]=='o') cnt++;
            }
        }
        return cnt;
    }

}