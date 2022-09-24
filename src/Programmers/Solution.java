package Programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    static int[] fireX = {1,-1,0,0,-1,-1,1,1};
    static int[] fireY = {0,0,1,-1,-1,1,-1,1};

    static int[] iceX = {1,-1,0,0};
    static int[] iceY = {0,0,1,-1};

    public static void main(String[] args){
        int[][] t1 = new int[1][2];
        t1[0][0] = 1;
        t1[0][1] = 1;
        int[][] t2 = new int[1][2];
        t2[0][0] = 3;
        t2[0][1] = 3;
        long[][] array1 = solution(3,2,t1,t2);
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array1[i].length;j++){
                System.out.print(array1[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static long[][] solution(int n, int m, int[][] fires, int[][] ices) {
        long[][] solution = new long[n][n];
        long[][] answer = new long[n+1][n+1];
        boolean[][][] chk1 = new boolean[fires.length][n+1][n+1];
        boolean[][][] chk2 = new boolean[ices.length][n+1][n+1];

        List<V> fire = new ArrayList<>();
        List<V> ice = new ArrayList<>();
        Object[] queue1 = new Object[fires.length];
        Object[] queue2 = new Object[ices.length];

        for(int i=0;i<fires.length;i++){
            Queue<V> queue = new PriorityQueue<>();
            queue.add(new V(fires[i][0],fires[i][1],1));
            chk1[i][fires[i][0]][fires[i][1]]=true;
            queue1[i] = queue;
            fire.add(new V(fires[i][0],fires[i][1],1));
        }

        for(int i=0;i<ices.length;i++){
            Queue<V> queue = new PriorityQueue<>();
            queue.add(new V(ices[i][0],ices[i][1],1));
            chk2[i][ices[i][0]][ices[i][1]]=true;
            queue2[i] = queue;
            ice.add(new V(ices[i][0],ices[i][1],1));
        }

        int start = 1;
        while(m-->0){
            for(int i=0;i<fires.length;i++){
                bfs(chk1[i], (Queue<V>) queue1[i],start,fire,n,'F');
            }
            for(int i=0;i<ices.length;i++){
                bfs(chk2[i], (Queue<V>) queue2[i],start,ice,n,'I');
            }

            for(V v : fire) answer[v.x][v.y]++;
            for(V v : ice) answer[v.x][v.y]--;

            start++;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                solution[i-1][j-1]=answer[i][j];
            }
        }
        return solution;
    }

    public static void bfs(boolean[][] check, Queue<V> queue, int level, List<V> list, int N, char ch){
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.level>level){
                queue.add(v);
                break;
            }

            if(ch=='F'){
                for(int i=0;i<8;i++){
                    int x = v.x+fireX[i];
                    int y = v.y+fireY[i];
                    if(x>N || x<=0 || y>N || y<=0 || check[x][y]) continue;
                    check[x][y]=true;
                    queue.add(new V(x,y,v.level+1));
                    list.add(new V(x,y,0));
                }
            }else{
                for(int i=0;i<4;i++){
                    int x = v.x+iceX[i];
                    int y = v.y+iceY[i];
                    if(x>N || x<=0 || y>N || y<=0 || check[x][y]) continue;
                    check[x][y]=true;
                    queue.add(new V(x,y,v.level+1));
                    list.add(new V(x,y,0));
                }
            }
        }

    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int level;
    public V(int x,int y,int level){
        this.x=x;
        this.y=y;
        this.level=level;
    }

    @Override
    public int compareTo(V o) {
        return this.level-o.level;
    }
}