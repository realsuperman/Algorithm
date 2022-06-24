package BaekJoon_1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,FIND,result;
    static int[] array;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        FIND = Integer.parseInt(str[1]);
        array = new int[N];
        check = new boolean[N];
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);
        solution(0,0);
        System.out.println(result);
    }
    public static void solution(int start,int number){
        if(number == N){
            int sum = 0;
            boolean sw = true;
            for(int i=0;i<N;i++){
                if(check[i]){
                    sum+=array[i]; sw=false;
                }
            }
            if(!sw&&sum==FIND) result++;
            return;
        }
        check[start] = true;
        solution(start+1,number+1);
        check[start] = false;
        solution(start+1,number+1);

    }
}