package BaekJoon_20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[] array = new int[N]; // 각 노드의 부모 여부
        for(int i=0;i<N;i++) array[i]=i; // 모든 노드의 부모는 자신임

        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            boolean chk = union(array,Integer.parseInt(str[0]),Integer.parseInt(str[1]));
            if(!chk) {System.out.println(i+1); return;}
        }
        System.out.println(0);
    }

    static int find(int[] array,int idx){
        if(array[idx] == idx)
            return idx;
        else
            return find(array,array[idx]);
    }
    static boolean union(int[] array,int idx1, int idx2){
        int p1 = find(array,idx1);
        int p2 = find(array,idx2);

        if(p1 == p2)
            return false;
        else{
            array[p2] = p1;
            return true;
        }
    }
}