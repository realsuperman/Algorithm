package BaekJoon_19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dx = {-1,-1,0,1,1,1,0,-1}; // 인덱스를 증가시 방향 돌리기 가능
    static int[] dy = {0,-1,-1,-1,0,1,1,1};

    static int[][] array = new int[4][4];
    static int[][] direction = new int[4][4];
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<4;i++){
            String[] str = br.readLine().split(" ");
            array[i][0] = Integer.parseInt(str[0]);
            direction[i][0] = Integer.parseInt(str[1])-1;
            array[i][1] = Integer.parseInt(str[2]);
            direction[i][1] = Integer.parseInt(str[3])-1;
            array[i][2] = Integer.parseInt(str[4]);
            direction[i][2] = Integer.parseInt(str[5])-1;
            array[i][3] = Integer.parseInt(str[6]);
            direction[i][3] = Integer.parseInt(str[7])-1;
        }

        dfs(0,0,score,array,direction);
        System.out.println(score);
    }

    public static void dfs(int targetX, int targetY, int sum,int[][] array, int[][] direction) {
        int[][] temp1 = new int[4][4]; for(int i=0;i<4;i++) for(int j=0;j<4;j++) temp1[i][j]=array[i][j];
        int[][] temp2 = new int[4][4]; for(int i=0;i<4;i++) for(int j=0;j<4;j++) temp2[i][j]=direction[i][j];
        int temp = temp2[targetX][targetY];
        sum+=array[targetX][targetY];
        temp1[targetX][targetY]=-1;
        temp2[targetX][targetY]=-1;

        moveFish(targetX, targetY, temp1, temp2);

        int x = targetX;
        int y = targetY;

        // 상어 이동로직 작성
        while(true) {
            x += dx[temp];
            y += dy[temp];
            if(x>=4 || x<0 || y>=4 || y<0) break;
            if(temp1[x][y]<1) continue;
            dfs(x,y,sum,temp1,temp2);
        }
        score = score<sum?sum:score;
    }

    private static void moveFish(int sharkX, int sharkY, int[][] array,int[][] direction) {
        for(int k=1;k<=16;k++) { // 물고기 번호
            for (int i = 0; i < 4; i++) {
                boolean loop = false;
                for (int j = 0; j < 4; j++) {
                    if(array[i][j]==k ){
                        changeFishLocation(i,j,sharkX,sharkY,array,direction);
                        loop = true;
                    }
                    if(loop) break;
                }
                if(loop) break;
            }
        }
    }

    private static void changeFishLocation(int x,int y,int sharkX, int sharkY, int[][] array ,int[][] direction){
        int directionNumber = direction[x][y];
        int loop = directionNumber;

        while(true) {
            int n = x+dx[directionNumber];
            int m = y+dy[directionNumber];

            if(n>=4 || n<0 || m>=4 || m<0 || (n==sharkX && m==sharkY)){
                if(++directionNumber==8) directionNumber = 0;
                direction[x][y]=directionNumber;

                if(loop == directionNumber) break;
                continue;
            }

            int tempArray = array[x][y];
            int tempDirection = direction[x][y];

            array[x][y] = array[n][m];
            direction[x][y] = direction[n][m];
            array[n][m] = tempArray;
            direction[n][m] = tempDirection;

            break;
        }
    }
}