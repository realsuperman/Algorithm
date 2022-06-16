package BaekJoon_1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            String[] str = br.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int K = Integer.parseInt(str[1]);
            int[] building = new int[N+1];
            List<List<Integer>> list = new ArrayList<>();
            int[] degree = new int[N+1];
            for(int j=0;j<=N;j++) list.add(new ArrayList<Integer>());
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) building[j+1] = Integer.parseInt(str[j]);
            for(int j=0;j<K;j++){
                str = br.readLine().split(" ");
                list.get(Integer.parseInt(str[0])).add(Integer.parseInt(str[1]));
                degree[Integer.parseInt(str[1])]++;
            }
            int value = Integer.parseInt(br.readLine());
            sb.append(solution(N,building,list,degree,value)+"\n");
        }
        System.out.println(sb);
    }

    public static int solution(int N,int[] building,List<List<Integer>> list,int[] degree,int value){
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N+1];
        for(int i=1;i<=N;i++) if(degree[i]==0){ result[i] = building[i]; queue.add(i); }

        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int i : list.get(v)){
                result[i] = Math.max(result[i],result[v]+building[i]);
                if(--degree[i]==0) queue.add(i);
            }
        }
        return result[value];
    }
}
