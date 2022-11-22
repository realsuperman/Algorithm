package BaekJoon_2644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
    static int N,start,end;
    static boolean[][] check;
    static boolean[] nodes;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new boolean[N];
        check = new boolean[N][N];

        String[] str = br.readLine().split(" ");
        start = Integer.parseInt(str[0])-1;
        end = Integer.parseInt(str[1])-1;
        int K = Integer.parseInt(br.readLine());
        while(K-->0){
            str = br.readLine().split(" ");
            check[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]=true;
            check[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1]=true;
        }
        nodes[start]=true;
        System.out.println(bfs(start));
    }

    private static int bfs(int start) {
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(start,0));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.value == end) return v.age;

            for(int i=0;i<N;i++){
                if(nodes[i] || !check[v.value][i]) continue;
                nodes[i]=true;
                queue.add(new V(i,v.age+1));
            }
        }

        return -1;
    }
}


class V{
    int value;
    int age;
    public V(int value,int age){
        this.value=value;
        this.age=age;
    }
}