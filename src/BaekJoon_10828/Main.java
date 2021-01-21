package BaekJoon_10828;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        String[] array;
        StringBuilder sb = new StringBuilder();
        int cnt = Integer.parseInt(br.readLine());
        Stack stack = new Stack(cnt);

        for(int i=0;i<cnt;i++){
            str = br.readLine();
            array = str.split(" ",2);
            if("push".equals(array[0])){
                stack.push(Integer.parseInt(array[1]));
            }else if("pop".equals(array[0])){
                sb.append(stack.pop()).append('\n');
            }else if("size".equals(array[0])){
                sb.append(stack.size()).append('\n');
            }else if("empty".equals(array[0])){
                sb.append(stack.empty()).append('\n');
            }else if("top".equals(array[0])){
                sb.append(stack.top()).append('\n');
            }
        }
        System.out.print(sb);
    }
}

class Stack{
    private int array[];
    private int index=-1;
    public Stack(int capacity){
        array = new int[capacity];
    }
    public void push(int x){
        array[++index] = x;
    }
    public int pop(){
        return index<0?-1:array[index--];
    }
    public int size(){
        return index+1;
    }
    public int empty(){
        return index<0?1:0;
    }
    public int top(){
        return index<0?-1:array[index];
    }
}