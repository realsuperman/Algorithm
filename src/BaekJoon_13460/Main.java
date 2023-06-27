package BaekJoon_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    static int N,M;
    static char[][] array;
    static int[] dx = {-1,1,0,0}; // 상,하,좌,우
    static int[] dy = {0,0,-1,1}; // 상,하,좌,우
    static Map<String,Boolean> map = new HashMap<>(); // 중복체크 해쉬맵

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new char[N][M];

        int rx=0, ry=0, bx=0, by=0;
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='B'){
                    bx=i;
                    by=j;
                }else if(array[i][j]=='R'){
                    rx=i;
                    ry=j;
                }
            }
        }

        Queue<V> queue = new LinkedList<>();
        for(int i=0;i<4;i++){
            queue.add(new V(rx,ry,bx,by,0,i)); // 초기 4방향 정의
            map.put(rx+","+ry+","+bx+","+by+","+i,true);
        }

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.time==10){System.out.println(-1); return;}

            boolean retry = false;
            boolean isMove = true;
            boolean isEnd = false;
            rx = v.rx;
            ry = v.ry;
            bx = v.bx;
            by = v.by;

            while(true){
                if(rx>=N || rx<0 || ry>=M || ry<0 || array[rx][ry]=='#'){ // 영역을 벗어나거나 벽이라면
                    rx-=dx[v.direction];
                    ry-=dy[v.direction];
                    break;
                }
                if(rx==bx && ry==by){rx-=dx[v.direction]; ry-=dy[v.direction]; retry = true; break;} // 진행 중에 다른 구슬이 있으면
                if(array[rx][ry]=='O') { rx=-1; ry=-1; isMove=false; isEnd=true; break;} // 홀에 빠진 케이스

                rx+=dx[v.direction];
                ry+=dy[v.direction];
            }

            while(true){
                if(bx>=N || bx<0 || by>=M || by<0 || array[bx][by]=='#' || (rx==bx && ry==by)){
                    bx-=dx[v.direction];
                    by-=dy[v.direction];
                    break;
                }
                if(array[bx][by]=='O') {bx=-1; by=-1; isMove=false; isEnd=false; break;} // blue구슬이 빠지면 안됨

                bx+=dx[v.direction];
                by+=dy[v.direction];
            }

            if(retry){
                while(true){
                    if(rx>=N || rx<0 || ry>=M || ry<0 || array[rx][ry]=='#' || (rx==bx && ry==by)){
                        rx-=dx[v.direction];
                        ry-=dy[v.direction];
                        break;
                    }
                    if(array[rx][ry]=='O') { // retry의 경우 blue랑 red가 최종 좌표가 같으므로 구멍에 빠져도 isEnd랑 isEnd는 설정하지 않음
                        break;
                    }

                    rx+=dx[v.direction];
                    ry+=dy[v.direction];
                }
            }

            if(isEnd){System.out.println(v.time+1); return;}
            if(isMove){
                for(int i=0;i<4;i++){
                    if(map.get(rx+","+ry+","+bx+","+by+","+i)==null){
                        map.put(rx+","+ry+","+bx+","+by+","+i,true);
                        queue.add(new V(rx,ry,bx,by,v.time+1,i)); // 초기 4방향 정의
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

class V{
    int rx,ry,bx,by,time,direction;
    public V(int rx,int ry,int bx, int by,int time,int direction){
        this.rx=rx;
        this.ry=ry;
        this.bx=bx;
        this.by=by;
        this.time=time;
        this.direction=direction;
    }
}