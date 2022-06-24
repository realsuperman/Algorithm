package BaekJoon_10819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static int[] array;
    static boolean[] check;
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        check = new boolean[N];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);
        List<Integer> list = new ArrayList<>();
        solution(0,0,list);
        System.out.println(result);
    }
    public static void solution(int start, int number, List<Integer> list){
        if(number==N){
            int sum = 0;
            for(int i=0;i<list.size()-1;i++) sum+=Math.abs(list.get(i)-list.get(i+1));
            if(result<sum) result=sum;
            return;
        }
        for(int i=start;i<N;i++){
            if(check[i]) continue;
            check[i]=true;
            list.add(array[i]);
            int size = list.size();
            solution(start,number+1,list);
            list.remove(size-1);
            check[i]=false;
        }
    }
}