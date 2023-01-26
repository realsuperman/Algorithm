package BaekJoon_20005.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,P,HP;
    static char[][] array;
    static Map<Character, Integer> dps = new HashMap<>();
    static boolean[][][] check;
    static boolean[] finish = new boolean[26]; // 각각 유저들의 도착여부(하나의 큐에서 사용하므로 반드시 필수임)
    static Queue<V> queue = new LinkedList<>();
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        P = Integer.parseInt(str[2]);
        array = new char[N][M];
        check = new boolean[26][N][M]; // a~z까지의 체크배열
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]>='a' && array[i][j]<='z'){ queue.add(new V(i,j,array[i][j],0)); check[array[i][j]-'a'][i][j]=true;}
            }
        }

        for(int i=0;i<P;i++) {
            str = br.readLine().split(" ");
            dps.put(str[0].charAt(0), Integer.valueOf(str[1]));
        }

        HP = Integer.parseInt(br.readLine());
        System.out.println(bfs());
    }

    public static int bfs(){
        int currDps = 0;
        int currTime = 0;
        int currUserCnt = 0;

        while(HP>0){
            HP-=currDps; // 피를 깍는다
            if(HP<0) return currUserCnt;

            while(!queue.isEmpty()){ // 유저들이 이동한다
                V v = queue.remove();
                if(finish[v.ch-'a']) continue; // 도착지점에 도달한경우 bfs를 실행하면 안됨
                if(array[v.x][v.y]=='B'){ finish[v.ch-'a']=true; currDps+=dps.get(v.ch); currUserCnt++; continue;}

                if(currTime<v.time){
                    queue.add(v);
                    if(queue.peek().time > currTime) break;
                }

                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x<0 || x>=N || y<0 || y>=M || check[v.ch-'a'][x][y] || array[x][y]=='X') continue;
                    check[v.ch-'a'][x][y]=true;
                    queue.add(new V(x,y,v.ch,v.time+1));
                }
            }

            currTime++;
        }
        return currUserCnt;
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    char ch;
    int time;
    public V(int x,int y,char ch,int time){
        this.x=x;
        this.y=y;
        this.ch=ch;
        this.time=time;
    }

    @Override
    public int compareTo(V o) {
        return this.time-o.time;
    }
}