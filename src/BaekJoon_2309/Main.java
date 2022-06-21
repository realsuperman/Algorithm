package BaekJoon_2309;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int[] array;
    static boolean[] check;
    static boolean value = false;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = new int[9];
        check = new boolean[9];
        for(int i=0;i<9;i++) array[i] = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        solution(0,list);
    }
    public static void solution(int num,List<Integer> list){
        if(num==7){
            int sum = 0;
            for(int i : list) sum+=i;
            if(sum==100){
                value=true;
                Collections.sort(list);
                for(int i : list) System.out.println(i);
            }
            return;
        }
        for(int i=0;i<9;i++){
            if(value) return;
            if(check[i]) continue;
            check[i]=true;
            list.add(array[i]);
            int size = list.size();
            solution(num+1,list);
            check[i]=false;
            list.remove(size-1);
        }
    }
}