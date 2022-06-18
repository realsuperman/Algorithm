package BaekJoon_21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static Queue<V> queue;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static int[] dx2 = {-1,-1,1,1};
    static int[] dy2 = {-1,1,1,-1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N+1][N+1];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                array[i+1][j+1]= Integer.parseInt(str[j]);
            }
        }

        queue = new LinkedList<>();
        queue.add(new V(N,1));
        queue.add(new V(N,2));
        queue.add(new V(N-1,1));
        queue.add(new V(N-1,2));

        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            solution(Integer.parseInt(str[0])-1,Integer.parseInt(str[1]));
        }

        int sum = 0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                sum+=array[i][j];
            }
        }
        System.out.println(sum);
    }
    public static void solution(int direction,int speed){
        Queue<V> temp = new LinkedList<>();
        boolean[][] check = new boolean[N+1][N+1];
        while(!queue.isEmpty()){
            V v = queue.remove();
            int x = v.x;
            int y = v.y;

            for(int i=0;i<speed;i++){
                int solutionX = x+dx[direction];
                int solutionY = y+dy[direction];
                if(solutionX>N) solutionX=1;
                else if(solutionX<=0) solutionX=N;
                if(solutionY>N) solutionY=1;
                else if(solutionY<=0) solutionY=N;
                x = solutionX;
                y = solutionY;
            }
            check[x][y]=true;
            array[x][y]+=1;
            temp.add(new V(x,y));
        }

        while(!temp.isEmpty()){
            V v = temp.remove();
            int x = v.x;
            int y = v.y;
            int cnt = 0;
            for(int i=0;i<4;i++){
                int solutionX = x+dx2[i];
                int solutionY = y+dy2[i];
                if(solutionX>N || solutionX<=0 || solutionY>N || solutionY<=0) continue;
                if(array[solutionX][solutionY]>=1) cnt++;
            }
            array[x][y]+=cnt;
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(!check[i][j] && array[i][j]>=2){
                    queue.add(new V(i,j));
                    array[i][j]-=2;
                }
            }
        }
    }
    public static void print(){
        System.out.println();
        System.out.println();
        System.out.println();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
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