package BaekJoon_14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static boolean[] check;
    static int[][] array;
    static int N;
    static int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        check = new boolean[N];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]=Integer.parseInt(str[j]);
            }
        }
        solution(0,0);
        System.out.println(MAX);
    }
    public static void solution(int start,int num){
        if(num==N/2){
            List<Integer> list = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            for(int i=0;i<N;i++){
                if(check[i]) list.add(i);
                else list2.add(i);
            }
            int sum1 = 0;
            int sum2 = 0;
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.size();j++){
                    if(list.get(i)==list.get(j)) continue;
                    sum1+=array[list.get(i)][list.get(j)];
                }
            }
            for(int i=0;i<list2.size();i++){
                for(int j=0;j<list2.size();j++){
                    if(list2.get(i)==list2.get(j)) continue;
                    sum2+=array[list2.get(i)][list2.get(j)];
                }
            }
            int hap = Math.abs(sum1-sum2);
            MAX = MAX>hap?hap:MAX;
            return;
        }
        for(int i=start;i<N;i++){
            if(check[i]) continue;
            check[i]=true;
            solution(i,num+1);
            check[i]=false;
        }
    }
}
