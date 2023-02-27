package BaekJoon_14503.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M,result;
    static int startX, startY, startDirection;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        str = br.readLine().split(" ");
        startX = Integer.parseInt(str[0]);
        startY = Integer.parseInt(str[1]);
        startDirection = Integer.parseInt(str[2]);

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        solution();
        System.out.println(result);
    }

    private static void solution() {
        int x = startX;
        int y = startY;
        int direction = startDirection;

        while(true){
            boolean chk = false;

            if(array[x][y]==0){ array[x][y]=-1; result++;}
            for(int i=0;i<4;i++){
                int index1 = dx[i]+x;
                int index2 = dy[i]+y;
                if(index1<0 || index1>=N || index2<0 || index2>=M || array[index1][index2]!=0) continue;
                chk = true;
                break;
            }

            if(chk){ // 3번 케이스
                if(direction==0){ // 북
                    direction = 3; //서
                    if(y-1>=0 && array[x][y-1]==0) y--;
                }else if(direction==1){ // 동
                    direction = 0;
                    if(x-1>=0 && array[x-1][y]==0) x--;
                }else if(direction==2){ // 남
                    direction = 1;
                    if(y+1<M && array[x][y+1]==0) y++;
                }else if(direction==3){ // 서
                    direction = 2;
                    if(x+1<N && array[x+1][y]==0) x++;
                }
            }else{ // 2번 케이스
                if(direction==0){ // 북
                    x++;
                    if(x>=N || array[x][y]==1) break;
                }else if(direction==1){ // 동
                    y--;
                    if(y<0 || array[x][y]==1) break;
                }else if(direction==2) { // 남
                    x--;
                    if(x<0 || array[x][y]==1) break;
                }else if(direction==3) { // 서
                    y++;
                    if(y>=M || array[x][y]==1) break;
                }
            }
        }
    }

}