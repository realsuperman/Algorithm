package BaekJoon_10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] array;
    static boolean[] check;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        check = new boolean[N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        solution(0, 0, 0, 0);
        System.out.println(result);
    }

    private static void solution(int index,int num,int target,int sum) {
        if(num==N-1){
            if(array[index][target]==0) return;
            sum+=array[index][target];
            if(result>sum) result = sum;
            return;
        }

        for(int i=0;i<N;i++){
            if(array[index][i]==0 || check[i] || i==target) continue;
            check[i]=true;
            solution(i,num+1,target,sum+array[index][i]);
            check[i]=false;
        }
    }
}