package BaekJoon_16197;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        char[][] array = new char[N][M];

        int[][] value = new int[2][2];
        int index = 0;

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='o'){
                    value[index][0]=i; value[index][1]=j;
                    index++;
                }
            }
        }

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(value[0][0],value[0][1],value[1][0],value[1][1],-1,0));

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.time>10) { System.out.println(-1); return;}

            if(v.direction!=-1){
                int x1 = v.coin1X;
                int y1 = v.coin1Y;
                int x2 = v.coin2X;
                int y2 = v.coin2Y;
                boolean chk = false;

                if(x1>=N || x1<0 || y1>=M || y1<0){
                    chk=true;
                    if(x2<N && x2>=0 && y2<M && y2>=0){
                        System.out.println(v.time);
                        return;
                    }
                }

                if(x2>=N || x2<0 || y2>=M || y2<0){
                    chk=true;
                    if(x1<N && x1>=0 && y1<M && y1>=0){
                        System.out.println(v.time);
                        return;
                    }
                }
                if(chk) continue;
            }

            for(int i=0;i<4;i++){
                int x1 = v.coin1X+dx[i];
                int y1 = v.coin1Y+dy[i];
                int x2 = v.coin2X+dx[i];
                int y2 = v.coin2Y+dy[i];

                int insertX1,insertY1,insertX2,insertY2;
                if(x1<N && x1>=0 && y1<M && y1>=0 && array[x1][y1]=='#'){
                    insertX1=v.coin1X;
                    insertY1=v.coin1Y;
                }else{
                    insertX1=x1;
                    insertY1=y1;
                }

                if(x2<N && x2>=0 && y2<M && y2>=0 && array[x2][y2]=='#'){
                    insertX2=v.coin2X;
                    insertY2=v.coin2Y;
                }else{
                    insertX2=x2;
                    insertY2=y2;
                }

                queue.add(new V(insertX1,insertY1,insertX2,insertY2,i,v.time+1));
            }
        }

    }
}

class V implements Comparable<V>{
    int coin1X; // 첫번째 코인의 X좌표
    int coin1Y; // 첫번째 코인의 Y좌표
    int coin2X;
    int coin2Y;
    int direction; // 방향(초기는 -1, 아래,위,오른쪽,왼쪽)
    int time; // 시간

    public V(int coin1X,int coin1Y,int coin2X,int coin2Y,int direction,int time){
        this.coin1X=coin1X;
        this.coin1Y=coin1Y;
        this.coin2X=coin2X;
        this.coin2Y=coin2Y;
        this.direction=direction;
        this.time=time;
    }

    @Override
    public int compareTo(V o) {
        return this.time-o.time;
    }
}