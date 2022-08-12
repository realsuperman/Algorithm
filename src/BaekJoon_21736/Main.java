package BaekJoon_21736;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        char[][] array = new char[N][M];
        boolean[][] check = new boolean[N][M];
        Queue<V> queue = new LinkedList<>();

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='I'){
                    queue.add(new V(i,j));
                    check[i][j]=true;
                    array[i][j]='O';
                }
            }
        }

        int cnt = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(array[v.x][v.y]=='P') cnt++;

            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]=='X') continue;

                check[x][y]=true;
                queue.add(new V(x,y));
            }
        }
        System.out.println(cnt==0?"TT":cnt);
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