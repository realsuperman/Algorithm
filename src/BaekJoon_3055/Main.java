package BaekJoon_3055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int R,C;
    static char[][] array;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        array = new char[R][C];
        boolean[][] hedgehogsCheck = new boolean[R][C];
        boolean[][] waterCheck = new boolean[R][C];

        Queue<V> hedgehogs = new LinkedList<>();
        Queue<V> water = new LinkedList<>();
        for(int i=0;i<R;i++){
            str = br.readLine().split("");
            for(int j=0;j<C;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='S'){
                    hedgehogs.add(new V(i,j,0));
                    hedgehogsCheck[i][j]=true;
                }else if(array[i][j]=='*'){
                    water.add(new V(i,j,0));
                    waterCheck[i][j]=true;
                }
            }
        }
        while(true){
            bfs(water,waterCheck,'D',1);
            boolean check = bfs(hedgehogs,hedgehogsCheck,'*',0);
            if(check) return;
            if(hedgehogs.isEmpty()) break;
        }
        System.out.println("KAKTUS");
    }
    public static boolean bfs(Queue<V> queue,boolean[][] check,char ch,int sw){
        int index = 0;
        int size = queue.size();
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(sw==0 && array[v.x][v.y]=='D'){System.out.println(v.time); return true;}
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=R || y<0 || y>=C) continue;
                if(!check[x][y] && array[x][y]!=ch && array[x][y]!='X'){
                    check[x][y]=true;
                    if(sw==1) array[x][y]='*';
                    queue.add(new V(x,y,v.time+1));
                }
            }
            index++;
            if(index>=size) break;
        }
        return false;
    }
}

class V{
    int x;
    int y;
    int time;
    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }
}