package BaekJoon_1389;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N+1][N+1];
        boolean[] check = new boolean[N+1];

        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
            array[Integer.parseInt(str[1])][Integer.parseInt(str[0])] = 1;
        }

        int[] finish = new int[N+1];
        int index=1;
        while(index<=N){
            for(int i=1;i<check.length;i++) check[i] = false;

            check[index] = true;
            Queue<V> queue = new LinkedList<>();
            int min = 0;
            for(int i=1;i<array[index].length;i++){
                if(array[index][i]==1){
                    min +=1;
                    check[i] = true;
                    V v = new V(i,2);
                    queue.add(v);
                }
            }

            while(!queue.isEmpty()){
                V v = queue.remove();
                for(int i=1;i<array[v.index].length;i++){
                    if(!check[i]&&array[v.index][i]==1){
                        min +=v.value;
                        check[i] = true;
                        queue.add(new V(i,v.value+1));
                    }
                }
            }
            finish[index++] = min;
        }

        int result1 = finish[1];
        int result2 = 1;

        for(int i=2;i<finish.length;i++){
            if(result1>finish[i]){
                result1 = finish[i];
                result2 = i;
            }
        }
        System.out.println(result2);
    }
}

class V{
    int index;
    int value;
    public V(int index,int value){
        this.index = index;
        this.value = value;
    }
}