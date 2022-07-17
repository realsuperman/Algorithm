package BaekJoon_17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,D,result;
    static int[][] array;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        D = Integer.parseInt(str[2]);
        array = new int[N][M];
        check = new boolean[M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        List<Integer> list = new ArrayList<>();
        solution(0,0,list);
        System.out.println(result);
    }

    public static void solution(int num,int start, List<Integer> list){
        if(num==3){
            int cnt = simulation(list);
            result = result<cnt?cnt:result;
            return;
        }

        for(int i=start;i<M;i++){
            if(check[i]) continue;
            check[i] = true;
            list.add(i);
            int size = list.size();
            solution(num+1,i,list);
            list.remove(size-1);
            check[i]=false;
        }
    }

    public static int simulation(List<Integer> list){
        int max = 0;
        int[][] temp = new int[N][M];
        int depth = 0;

        for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

        while (isExist(temp)) {
            Set<String> set = new HashSet<>();
            for(int index : list){
                Queue<V> queue = new PriorityQueue<>();

                for(int i=0;i<N;i++){
                    for(int j=0;j<M;j++){
                        int d = Math.abs(N-i)+Math.abs(index-j);
                        if(temp[i][j]==1 && d<=D ){
                            queue.add(new V(i,j,d));
                        }
                    }
                }
                if(queue.size()>0) set.add(queue.peek().x+" "+queue.peek().y);
            }
            max+=set.size();
            for(String s : set){
                String[] str = s.split(" ");
                temp[Integer.parseInt(str[0])][Integer.parseInt(str[1])]=0;
            }

            for(int i=N-1;i>0;i--){
                for(int j=0;j<M;j++){
                    temp[i][j] = temp[i-1][j];
                }
            }

            for(int i=0;i<M;i++) temp[depth][i] = 0;
            depth++;
        }

        return max;
    }

    public static boolean isExist(int[][] temp){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j]==1) return true;
            }
        }

        return false;
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int d;
    public V(int x,int y,int d){
        this.x=x;
        this.y=y;
        this.d=d;
    }

    @Override
    public int compareTo(V o) {
        if(this.d==o.d){
            return this.y-o.y;
        }
        return this.d-o.d;
    }
}