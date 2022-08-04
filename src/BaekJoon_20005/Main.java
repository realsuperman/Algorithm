package BaekJoon_20005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Set<String> set;
    static char[][] array;
    static int N,M,P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        P = Integer.parseInt(str[2]);
        Map<String,Integer> map = new LinkedHashMap<>();

        array = new char[N][M];
        Object[] objects = new Object[26];
        boolean[][][] check = new boolean[26][N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]>=97 && array[i][j]<123){
                    Queue<V> queue = new PriorityQueue<>();
                    queue.add(new V(i,j,array[i][j],0));
                    objects[array[i][j]-97] = queue;
                    check[array[i][j]-97][i][j]=true;
                }
            }
        }

        for(int i=0;i<P;i++){
            str = br.readLine().split(" ");
            map.put(str[0], Integer.valueOf(str[1]));
        }
        int HP = Integer.parseInt(br.readLine());
        set = new HashSet<>();

        int depth = 0;
        while(true){
            for(String s : set){
                HP-=map.get(s);
                if(HP<=0){System.out.println(set.size()); return;}
            }

            for(String s : map.keySet()){
                if(!set.contains(s)){
                    char ch = s.charAt(0);
                    bfs((Queue<V>) objects[ch-97],check,depth);
                }
            }


            depth++;
        }
    }

    public static void bfs(Queue<V> queue,boolean[][][] check,int depth){
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.time!=depth){ queue.add(v); break;}
            if(array[v.x][v.y]=='B'){
                set.add(String.valueOf(v.ch));
                continue;
            }
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=M || check[v.ch-97][x][y]|| array[x][y]=='X') continue;
                check[v.ch-97][x][y] = true;
                queue.add(new V(x,y,v.ch,v.time+1));
            }
        }
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
        if(this.time == o.time){
            return this.ch-o.ch;
        }
        return this.time-o.time;
    }
}