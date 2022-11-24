package BaekJoon_1389.retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    static int N;
    static boolean[][] connect;
    static int value = Integer.MAX_VALUE;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        connect = new boolean[N][N];
        Set<String> set = new HashSet<>();

        while(M-->0) set.add(br.readLine());

        for(String v : set){
            str = v.split(" ");
            connect[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1] = true;
            connect[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = true;
        }

        for(int i=0;i<N;i++){
            int kebinNumber=0;

            for(int j=0;j<N;j++){
                if(i==j) continue;
                kebinNumber+=solution(i,j);
            }
            if(kebinNumber<value){ result = i; value = kebinNumber; }
        }

        System.out.println(result+1);
    }

    public static int solution(int node,int target){
        int value=0;
        boolean[] check = new boolean[N];
        check[node]=true;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(node,0));

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.v==target){ value=v.cnt; break;}

            for(int i=0;i<N;i++){
                if(connect[i][v.v] && !check[i]){ queue.add(new V(i,v.cnt+1)); check[i]=true;}
            }
        }

        return value;
    }
}

class V{
    int v;
    int cnt;
    public V(int v,int cnt){this.v=v; this.cnt=cnt;}
}