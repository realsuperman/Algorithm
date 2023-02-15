package BaekJoon_21608.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] array;
    static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        for(int i=0;i<N*N;i++){
            String[] str = br.readLine().split(" ");
            List<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(str[1]));
            list.add(Integer.parseInt(str[2]));
            list.add(Integer.parseInt(str[3]));
            list.add(Integer.parseInt(str[4]));
            assign(Integer.parseInt(str[0]), list);
            map.put(Integer.parseInt(str[0]),list);
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] connectCount = isConnectCount(i, j, map.get(array[i][j]));
                if(connectCount[0]==4) sum+=1000;
                else if(connectCount[0]==3) sum+=100;
                else if(connectCount[0]==2) sum+=10;
                else if(connectCount[0]==1) sum+=1;
            }
        }

        System.out.println(sum);
    }

    public static void assign(int studentId, List<Integer> list){
        Queue<V> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==0){
                    int[] result = isConnectCount(i,j,list);
                    queue.add(new V(result[0],result[1],i,j));
                }
            }
        }
        V v = queue.remove();
        array[v.x][v.y]=studentId;
    }

    public static int[] isConnectCount(int fromX,int fromY,List<Integer> list){
        int[] result = new int[2];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int x = Math.abs(fromX-i);
                int y = Math.abs(fromY-j);
                if(x+y==1){
                    if(list.contains(array[i][j])) result[0]++;
                    else if(!list.contains(array[i][j]) && array[i][j]==0) result[1]++;
                }
            }
        }
        return result;
    }
}

class V implements Comparable<V>{
    int likeCnt;
    int emptyCnt;
    int x;
    int y;

    public V(int likeCnt, int emptyCnt, int x, int y){
        this.likeCnt=likeCnt;
        this.emptyCnt=emptyCnt;
        this.x=x;
        this.y=y;
    }

    @Override
    public int compareTo(V o) {
        if(this.likeCnt==o.likeCnt){
            if(this.emptyCnt==o.emptyCnt){
                if(this.x==o.x){
                    return this.y-o.y;
                }
                return this.x-o.x;
            }
            return o.emptyCnt-this.emptyCnt;
        }
        return o.likeCnt-this.likeCnt;
    }
}