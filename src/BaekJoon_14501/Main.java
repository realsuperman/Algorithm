package BaekJoon_14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N;
    static int end;
    static int[][] array;
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N+1][N+1];
        end = N+1;
        for(int i=1;i<=N;i++){
            String[] str = br.readLine().split(" ");
            array[i][0]=Integer.parseInt(str[0]); // 소요일
            array[i][1]=Integer.parseInt(str[1]); // 금액
        }
        List<V> list = new ArrayList<>();
        solution(1,list);
        System.out.println(MAX);
    }
    public static void solution(int start,List<V> list){
        if(list!=null && start<=end){
            int daySum = 0;
            int goldSum = 0;
            for(V v: list){
                daySum+=v.time;
                goldSum+=v.gold;
            }
            if(daySum<=end && goldSum>MAX) MAX=goldSum;
        }
        for(int i=start;i<=N;i++){
            list.add(new V(array[i][0],array[i][1]));
            int size = list.size();
            solution(i+array[i][0],list);
            list.remove(size-1);
        }
    }
}

class V{
    int time;
    int gold;
    public V(int time,int gold){
        this.time=time;
        this.gold=gold;
    }
}