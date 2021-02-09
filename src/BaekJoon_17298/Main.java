package BaekJoon_17298;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int[] array = new int[n];
        for(int i=0;i<n;i++) list.add(Integer.parseInt(str[i]));


        int i=n-1;
        while(i>=0){
            boolean sw = false;
            while(!stack.isEmpty()){
                if(list.get(i)<stack.peek()){
                    sw = true;
                    array[i] = stack.peek();
                    break;
                }else{
                    stack.pop();
                }
            }
            if(!sw) array[i] = -1;
            stack.push(list.get(i));
            i--;
        }
        for(int k : array) sb.append(k+" ");
        System.out.print(sb);
    }
}