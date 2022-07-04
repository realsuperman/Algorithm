package BaekJoon_6087;

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
        int N = Integer.parseInt(str[1]);
        int M = Integer.parseInt(str[0]);
        char[][] array = new char[N][M];
        boolean chk = false;
        int[][][] check = new int[N][M][1]; // N,M에 몇의 값(time)으로 방문하였는가
        Queue<V> queue = new LinkedList<>();

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
                if(!chk && array[i][j]=='C'){
                    chk=true;
                    queue.add(new V(i,j,0,4));
                    check[i][j][0] = 0;
                }else{
                    check[i][j][0]=Integer.MAX_VALUE;
                }
            }
        }

        int startX = queue.peek().x;
        int startY = queue.peek().y;
        int targetX=0,targetY=0;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.direction!='E' && array[v.x][v.y]=='C'){
                targetX = v.x;
                targetY = v.y;
                if(v.time<check[v.x][v.y][0]) check[v.x][v.y][0]=v.time;
                continue;
            }

            int direction;
            if(v.direction=='D') direction=0;
            else if(v.direction=='U') direction=1;
            else if(v.direction=='R') direction=2;
            else if(v.direction=='L') direction=3;
            else direction=4;

            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=N || y<0 || y>=M || array[x][y]=='*') continue;
                if(direction==4) {
                    check[x][y][0]=v.time;
                    queue.add(new V(x,y,v.time,i));
                }else{
                    if(direction==i && check[x][y][0]>=v.time){
                        check[x][y][0]=v.time;
                        queue.add(new V(x,y,v.time,i));
                    }else if(direction!=i && check[x][y][0]>=v.time+1){
                        check[x][y][0]=v.time+1;
                        queue.add(new V(x,y,v.time+1,i));
                    }
                }
            }

        }

        System.out.println(check[targetX][targetY][0]);
    }
}

class V{
    int x;
    int y;
    int time;
    char direction; // D,U,R,L

    public V(int x,int y,int time,int direction){
        this.x=x;
        this.y=y;
        this.time=time;
        switch(direction){
            case 0 : this.direction = 'D'; break;
            case 1 : this.direction = 'U'; break;
            case 2 : this.direction = 'R'; break;
            case 3 : this.direction='L'; break;
            default: this.direction='E';
        }
    }
}