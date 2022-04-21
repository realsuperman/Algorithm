package BaekJoon_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int W[];
    public static int V[];
    public static Integer[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int max = Integer.parseInt(str[1]);
        array = new Integer[N][max+1];
        W = new int[N];
        V = new int[N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            W[i] = Integer.parseInt(str[0]);
            V[i] = Integer.parseInt(str[1]);
        }
        System.out.println(topDown(max,N-1));
    }
    public static int topDown(int weight,int index){
        if(index<0) return 0;

        if(array[index][weight]==null){
            if(W[index]>weight){
                array[index][weight] = topDown(weight,index-1);
            }else{
                int a = V[index]+topDown(weight-W[index],index-1);
                int b = topDown(weight,index-1);
                array[index][weight] = a>b?a:b;
            }
        }
        return array[index][weight];
    }
}