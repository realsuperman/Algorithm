package BaekJoon_1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int SIZE = Integer.parseInt(br.readLine());
            V[] v = new V[SIZE];
            for(int j=0;j<SIZE;j++){
                String[] str =br.readLine().split(" ");
                v[j] = new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
            }
            System.out.println(solution(v));
        }

    }
    public static int solution(V[] v){
        Arrays.sort(v);
        int result = 1;
        int max = v[0].y;
        for(int i=1;i<v.length;i++){
            if(v[i].y<max){
                result++;
                max = v[i].y;
            }
        }
        return result;
    }
}

class V implements Comparable<V>{
    int x;
    int y;

    public V(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public int compareTo(V o) {
        return this.x-o.x;
    }
}