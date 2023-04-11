package BaekJoon_15684.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M;
    static boolean[][] array;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[2]); // 가로
        M = Integer.parseInt(str[0]); // 세로
        array = new boolean[N][M];

        int H = Integer.parseInt(str[1]); // 초기값
        while(H-->0){
            str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0])-1;
            int to = Integer.parseInt(str[1])-1;

            array[from][to]=true;
        }

        if(isFinish()){ System.out.println(0);return;}
        else for(int i=1;i<=3;i++) dfs(i,0, 0, N*M);
        System.out.println(result==Integer.MAX_VALUE?-1:result);
    }

    public static void dfs(int depth, int currentDepth, int start, int max){
        if(depth==currentDepth){
            if(isFinish()) result = result>depth?depth:result;
            return;
        }

        //합당한 선을 뽑아야함
        for(int i=start;i<max;i++){
            int x = i/M;
            int y = i%M;
            if(y==M-1 || array[x][y] || (y-1>=0 && array[x][y-1]) || (y+1<M && array[x][y+1])) continue;
            array[x][y]=true;
            dfs(depth,currentDepth+1, i+1,max);
            array[x][y]=false;
        }
    }

    private static boolean isFinish(){
        int y = 0;
        while(y<M) {
            int x = 0;
            int tempY = y;
            while(x<N) {
                if (array[x][tempY]) tempY++;
                else if(tempY-1>=0 && array[x][tempY-1]) tempY--;
                x++;
            }
            if(tempY!=y) return false;

            y++;
        }
        return true;
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}