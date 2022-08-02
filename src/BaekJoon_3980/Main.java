package BaekJoon_3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] array;
    static boolean[] check;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++){
            array = new int[11][11];
            check = new boolean[11];
            result = Integer.MIN_VALUE;
            for(int j=0;j<11;j++){
                String[] str = br.readLine().split(" ");
                for(int k=0;k<str.length;k++) array[j][k]= Integer.parseInt(str[k]);
            }
            solution(0,0);
            sb.append(result+"\n");
        }
        System.out.println(sb);
    }

    public static void solution(int seat,int sum){
        if(seat==11){
            if(sum>result) result=sum;
            return;
        }

        for(int i=0;i<11;i++){
            if(check[i] || array[i][seat]==0) continue;
            check[i]=true;
            solution(seat+1,sum+array[i][seat]);
            check[i]=false;
        }
    }
}