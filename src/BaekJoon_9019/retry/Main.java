package BaekJoon_9019.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int target;
    static boolean[] check;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            String[] str = br.readLine().split(" ");
            target = Integer.parseInt(str[1]);
            check = new boolean[10000];
            check[Integer.parseInt(str[0])]=true;
            solution(str[0]);
        }
        System.out.println(sb);
    }

    public static void solution(String str){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(Integer.parseInt(str),new StringBuilder()));

        while(!queue.isEmpty()){
            V v = queue.remove();
            StringBuilder temp = new StringBuilder(String.valueOf(v.v));
            if(v.v == target) {sb.append(v.command); break;}


            int value = ((v.v*2)%10000);
            checkMethod(queue, v, value,"D");

            value = v.v-1;
            if(value==0) value=9999;
            checkMethod(queue, v, value,"S");

            int v1 = temp.charAt(0)-'0';
            int v2 = temp.charAt(1)-'0';
            int v3 = temp.charAt(2)-'0';
            int v4 = temp.charAt(3)-'0';
            value = cal(v1,v2,v3,v4,'L');
            checkMethod(queue, v, value,"L");

            value = cal(v1,v2,v3,v4,'R');
            checkMethod(queue, v, value,"R");
        }
    }

    private static void checkMethod(Queue<V> queue, V v, int value, String op) {
        if(!check[value]){
            check[value]=true;
            v.command.append(op);
            queue.add(new V(value,v.command));
            v.command.deleteCharAt(v.command.length()-1);
        }
    }

    public static int cal(int v1, int v2, int v3,int v4, char op){
        if (op == 'L')  return  ((v2 * 10 + v3) * 10 + v4) * 10 + v1;
        return ((v4 * 10 + v1) * 10 + v2) * 10 + v3;
    }
}

class V{
    int v;
    StringBuilder command;

    public V(int v,StringBuilder command){
        this.v=v;
        this.command=command;
    }
}