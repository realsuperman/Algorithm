package BaekJoon_14890;

import java.io.*;
public class Main {
    static int N,L;
    static int[][] array;
    static int result;
    static boolean[][][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        L = Integer.parseInt(str[1]);
        array = new int[N][N];
        check = new boolean[N][N][2]; // 그 좌표 방문여부(행,열) 처리 여부까지
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!check[i][j][1]){ // 행방향 진행안한경우
                    for(int column=j;column<N;column++) check[i][column][1]=true;
                    solution(i,j,true);
                }
                if(!check[i][j][0]) { // 열방향 진행안한경우
                    for (int row = i; row < N; row++) check[row][j][0] = true;
                    solution(i, j, false);
                }
            }
        }

        System.out.println(result);
    }

    public static void solution(int startX, int startY,boolean isRow){
        int x = startX;
        int y = startY;
        boolean[][] chk = new boolean[N][N];

        if(isRow){
            while(y<N-1){
                if(array[x][y]!=array[x][y+1]){ // 갈 수 없는 경우 경사로를 만들 수 있는지 판단함
                    if(array[x][y]+1 == array[x][y+1]){ // 1 2 ( 높아지는 경우 ) 왼쪽을 L만큼 체크해야함
                        for(int i=0;i<L;i++){
                            int tempY = y-i;
                            if(tempY<0 || chk[x][tempY] || array[x][tempY]!=array[x][y]) return;
                            chk[x][tempY]=true;
                        }
                        y++;
                    }else if(array[x][y]-1 == array[x][y+1]){ // 2 1 ( 낮아지는 경우 )
                        for(int i=1;i<=L;i++){
                            int tempY = y+i;
                            if(tempY>=N || chk[x][tempY] || array[x][tempY]!=array[x][y+1]) return;
                            chk[x][tempY]=true;
                        }
                        y+=L;
                    }else{ // 높이 차이가 2 이상
                        return;
                    }
                }else{
                    y++;
                }
            }
        }else{
            while(x<N-1){
                if(array[x][y]!=array[x+1][y]){ // 갈 수 없는 경우 경사로를 만들 수 있는지 판단함
                    if(array[x][y]+1 == array[x+1][y]){ // 1 2 ( 높아지는 경우 ) 왼쪽을 L만큼 체크해야함
                        for(int i=0;i<L;i++){
                            int tempX = x-i;
                            if(tempX<0 || chk[tempX][y] || array[tempX][y]!=array[x][y]) return;
                            chk[tempX][y]=true;
                        }
                        x++;
                    }else if(array[x][y]-1 == array[x+1][y]){ // 2 1 ( 낮아지는 경우 )
                        for(int i=1;i<=L;i++){
                            int tempX = x+i;
                            if(tempX>=N || chk[tempX][y] || array[tempX][y]!=array[x+1][y]) return;
                            chk[tempX][y]=true;
                        }
                        x+=L;
                    }else{ // 높이 차이가 2 이상
                        return;
                    }
                }else{
                    x++;
                }
            }
        }
        result++;
    }
}