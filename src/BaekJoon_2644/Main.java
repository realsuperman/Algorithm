package BaekJoon_2644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception{
        int n;
        int people1,people2;
        String[] str;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        boolean[][] array = new boolean[n+1][n+1];
        boolean[] check = new boolean[n+1];

        str = br.readLine().split(" ");
        people1 = Integer.parseInt(str[0]);
        people2 = Integer.parseInt(str[1]);
        if(people1==people2){System.out.println(0);return;}
        int m = Integer.parseInt(br.readLine());

        for(int i=0;i<m;i++) {
            str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = true;
            array[Integer.parseInt(str[1])][Integer.parseInt(str[0])] = true;
        }

        Queue<Value> queue = new LinkedList<>();
        check[people1] = true;
        for(int i=0;i<array[people1].length;i++){
            if(array[people1][i]){
                Value value = new Value(i,1);
                queue.add(value);
                check[i] = true;
            }
        }
        while(!queue.isEmpty()){
            Value value = queue.remove();
            check[value.value] = true;
            if(value.value == people2){ System.out.println(value.depth); return;}

            for(int i=0;i<array[value.value].length;i++){
                if(!check[i]&&array[value.value][i]){
                    Value t = new Value(i,value.depth+1);
                    queue.add(t);
                    check[i] = true;
                }
            }
        }
        System.out.println(-1);
    }
}

class Value{
    int value;
    int depth;
    public Value(int value,int depth){this.value=value; this.depth=depth;}
}