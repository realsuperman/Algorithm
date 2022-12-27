package BaekJoon_17471.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int[] array;
    static int N;
    static boolean[][] connect;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        connect = new boolean[N][N];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i]= Integer.parseInt(str[i]);
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=1;j<str.length;j++){
                connect[i][Integer.parseInt(str[j])-1] = true;
                connect[Integer.parseInt(str[j])-1][i] = true;
            }
        }

        for(int i=1;i<=N-1;i++){
            solution(0,0,i,new ArrayList<>());
        }

        System.out.println(MIN == Integer.MAX_VALUE?-1:MIN);
    }

    public static void solution(int index, int start, int depth, List<Integer> list){
        if(start==depth){
            bfs(list);
            return;
        }

        for(int i=index;i<N;i++){
            list.add(i);
            int size = list.size()-1;
            solution(i+1,start+1,depth,list);
            list.remove(size);
        }
    }

    private static void bfs(List<Integer> group1) {
        List<Integer> group2 = new ArrayList<>();
        for(int i=0;i<N;i++){
            boolean sw = true;
            for(int v : group1) if(v==i) sw=false;
            if(sw) group2.add(i);
        }

        boolean v1 = searchGraph(group1);
        boolean v2 = searchGraph(group2);
        if(v1 && v2){
            int sum1 = 0;
            int sum2 = 0;
            for(int v : group1) sum1+=array[v];
            for(int v : group2) sum2+=array[v];
            MIN = MIN>Math.abs(sum1-sum2)?Math.abs(sum1-sum2):MIN;
        }
    }

    private static boolean searchGraph(List<Integer> list){
        if(list.size()==1) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(list.get(0));

        boolean[] check = new boolean[N];
        check[queue.peek()]=true;
        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int i=0;i<N;i++){
                boolean chk = false;
                for(int test : list){ if(test==i){ chk=true; break;} }
                if(connect[v][i] && !check[i] && chk){
                    check[i]=true;
                    queue.add(i);
                }
            }
        }
        int cnt = 0;
        for(boolean v : check) if(v) cnt++;
        if(cnt==list.size()) return true;
        return false;
    }

}