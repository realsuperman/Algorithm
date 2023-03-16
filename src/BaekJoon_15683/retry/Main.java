package BaekJoon_15683.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] array;
    static int N,M;
    static int[] dx = {-1,1,0,0}; // 상 하 좌 우
    static int[] dy = {0,0,-1,1}; // 상 하 좌 우
    static List<V> list = new ArrayList<>();
    static int depth;
    static int MIN_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
                if(array[i][j]>=1 && array[i][j]<=5) list.add(new V(i,j,array[i][j],-1));
            }
        }
        depth = list.size();
        dfs(0,0,new ArrayList<>());
        System.out.println(MIN_VALUE==Integer.MAX_VALUE?0:MIN_VALUE);
    }

    public static void dfs(int currentDepth,int index, List<V> coordinationList){
        if(currentDepth==depth){
            int[][] temp = new int[N][M];
            List<V> calCoordination = new ArrayList<>();

            for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

            for(V v : coordinationList) bfs(v.x,v.y,v.direction,v.loop,calCoordination);
            for(V v : calCoordination) temp[v.x][v.y]=-1;
            int v = calVacantRoom(temp);
            MIN_VALUE = MIN_VALUE>v ? v : MIN_VALUE;
            return;
        }

        if(list.get(index).direction==1 || list.get(index).direction==3 || list.get(index).direction==4){
            for(int i=0;i<4;i++){
                coordinationList.add(new V(list.get(index).x,list.get(index).y,list.get(index).direction,i));
                int size = coordinationList.size()-1;
                dfs(currentDepth+1, index+1, coordinationList);
                coordinationList.remove(size);
            }
        }else if(list.get(index).direction==2){
            for(int i=0;i<2;i++){
                coordinationList.add(new V(list.get(index).x,list.get(index).y,list.get(index).direction,i));
                int size = coordinationList.size()-1;
                dfs(currentDepth+1, index+1, coordinationList);
                coordinationList.remove(size);
            }
        }else if(list.get(index).direction==5){
            coordinationList.add(new V(list.get(index).x,list.get(index).y,list.get(index).direction,0));
            int size = coordinationList.size()-1;
            dfs(currentDepth+1, index+1, coordinationList);
            coordinationList.remove(size);
        }
    }

    public static void bfs(int x,int y, int direction, int loop, List<V> calCoordination){
        if(direction==1){ // [up], [down], [left], [right]
            addCoordination(x,y,loop, calCoordination);
        }else if(direction==2){ // [left,right], [up,down]
            if(loop==0){
                addCoordination(x,y,2,calCoordination);
                addCoordination(x,y,3,calCoordination);
            }else if(loop==1){
                addCoordination(x,y,0,calCoordination);
                addCoordination(x,y,1,calCoordination);
            }
        }else if(direction==3){ // [up,right], [right,down], [down,left], [left,up]
            if(loop==0){
                addCoordination(x,y,0,calCoordination);
                addCoordination(x,y,3,calCoordination);
            }else if(loop==1){
                addCoordination(x,y,3,calCoordination);
                addCoordination(x,y,1,calCoordination);
            }else if(loop==2){
                addCoordination(x,y,1,calCoordination);
                addCoordination(x,y,2,calCoordination);
            }else if(loop==3){
                addCoordination(x,y,0,calCoordination);
                addCoordination(x,y,2,calCoordination);
            }
        }else if(direction==4){ // [left,right,up], [up,right,down], [right,down,left], [down,left,up]
            if(loop==0){
                addCoordination(x,y,0,calCoordination);
                addCoordination(x,y,2,calCoordination);
                addCoordination(x,y,3,calCoordination);
            }else if(loop==1){
                addCoordination(x,y,0,calCoordination);
                addCoordination(x,y,1,calCoordination);
                addCoordination(x,y,3,calCoordination);
            }else if(loop==2){
                addCoordination(x,y,3,calCoordination);
                addCoordination(x,y,1,calCoordination);
                addCoordination(x,y,2,calCoordination);
            }else if(loop==3){
                addCoordination(x,y,1,calCoordination);
                addCoordination(x,y,2,calCoordination);
                addCoordination(x,y,0,calCoordination);
            }
        }else if(direction==5){ // [up,down,left,right]
            addCoordination(x,y,0,calCoordination);
            addCoordination(x,y,1,calCoordination);
            addCoordination(x,y,2,calCoordination);
            addCoordination(x,y,3,calCoordination);
        }
    }

    public static void addCoordination(int startX,int startY,int direction, List<V> calCoordination){
        int x = startX;
        int y = startY;
        while(true){ // direction -> 상, 하, 좌, 우
            x = x+dx[direction];
            y = y+dy[direction];
            if(x>=N || x<0 || y>=M || y<0 || array[x][y]==6) break;
            calCoordination.add(new V(x,y,-1,-1));
        }
    }

    public static int calVacantRoom(int[][] arr){
        int count = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j]==0) count++;
            }
        }
        return count;
    }
}

class V{
    int x;
    int y;
    int direction;
    int loop;
    public V(int x, int y, int direction,int loop){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.loop=loop;
    }
}