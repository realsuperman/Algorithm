package Programmers_4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int answer = 0;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{-2,3,0,2,-5}));
        System.out.println(solution(new int[]{-3,-2,-1,0,1,2,3}));
        System.out.println(solution(new int[]{-1,1,-1,1}));
    }

    public static int solution(int[] number) {
        answer = 0;
        check = new boolean[number.length];
        dfs(0,new ArrayList<>(),0,number);
        return answer;
    }

    public static void dfs(int start, List<Integer> list,int index,int[] array){
        if(start==3){
            int sum=Integer.MAX_VALUE;
            for(int v : list){
                if(sum==Integer.MAX_VALUE) sum=v;
                else sum+=v;
            }
            if(sum==0) answer++;
            return;
        }

        for(int i=index;i<array.length;i++){
            if(check[i]) continue;
            check[i]=true;
            list.add(array[i]);
            int size = list.size();
            dfs(start+1,list,i,array);
            list.remove(size-1);
            check[i]=false;
        }

    }
}