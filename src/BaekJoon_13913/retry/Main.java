package BaekJoon_13913.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int from = Integer.parseInt(str[0]);
        int to = Integer.parseInt(str[1]);
        if(from>to){
            System.out.println(from-to);
            for(int i=from;i>=0;i--){
                System.out.print(i+" ");
                if(i==to) break;
            }
            System.out.println();
            return;
        }

        boolean[] check = new boolean[100001];
        check[from]=true;

        Queue<V> queue = new LinkedList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(from);
        queue.add(new V(from,0,temp));
        int result = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.value == to){
                result=v.time;
                list = v.list;
                break;
            }


            if(checkMethod(v.value+1,check)){List<Integer> paste = new ArrayList<>(); for(int val : v.list) paste.add(val); paste.add(v.value+1); queue.add(new V(v.value+1,v.time+1,paste)); }
            if(checkMethod(v.value-1,check)){List<Integer> paste = new ArrayList<>(); for(int val : v.list) paste.add(val); paste.add(v.value-1); queue.add(new V(v.value-1,v.time+1,paste));}
            if(checkMethod(v.value*2,check)){List<Integer> paste = new ArrayList<>(); for(int val : v.list) paste.add(val); paste.add(v.value*2); queue.add(new V(v.value*2,v.time+1,paste));}

        }

        System.out.println(result);
        StringBuilder sb = new StringBuilder();
        for(int v : list) sb.append(v+" ");
        System.out.println(sb);
    }

    public static boolean checkMethod(int value, boolean[] check){
        if(value>=0 && value<=100000 && !check[value]){
            check[value]=true;
            return true;
        }
        return false;
    }
}

class V{
    int value;
    int time;
    List<Integer> list;
    public V(int value, int time, List<Integer> list){
        this.value = value;
        this.time = time;
        this.list = list;
    }
}