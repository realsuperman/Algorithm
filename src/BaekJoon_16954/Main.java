package BaekJoon_16954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,1,-1,-1,1,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] array = new char[8][8];
        for(int i=0;i<8;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<8;j++) array[i][j]=str[j].charAt(0);
        }

        Queue<V> queue = new PriorityQueue<>();
        boolean[][][] check = new boolean[8][8][8];

        int time = 0;
        check[0][7][0]=true;
        queue.add(new V(7,0,0));

        while(true) {
            if(queue.isEmpty()) break;

            while (!queue.isEmpty()) {
                V v = queue.remove();
                if(v.time>time){ queue.add(v); break;}

                if (v.x == 0 && v.y == 7) {
                    System.out.println(1);
                    return;
                }

                if(array[v.x][v.y]=='#') continue;
                queue.add(new V(v.x,v.y,v.time+1));
                for (int i = 0; i < 8; i++) {
                    int x = dx[i] + v.x;
                    int y = dy[i] + v.y;
                    int t;
                    if(time>=8) t=7;
                    else t = time;

                    if (x < 0 || x >= 8 || y < 0 || y >= 8 || check[t][x][y] || array[x][y]=='#') continue;
                    check[t][x][y] = true;
                    queue.add(new V(x, y, v.time + 1));
                }
            }
            downWall(array);
            time++;
        }
        System.out.println(0);
    }

    public static void downWall(char[][] array){
        for(int i=0;i<8;i++) array[7][i]='.';

        for(int i=7;i>0;i--){
            for(int j=0;j<8;j++){
                array[i][j]='.';
            }
            for(int j=0;j<8;j++){
                array[i][j]=array[i-1][j];
            }
        }

        for(int i=0;i<8;i++) array[0][i]='.';
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int time;
    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }

    @Override
    public int compareTo(V o) {
        return this.time-o.time;
    }
}