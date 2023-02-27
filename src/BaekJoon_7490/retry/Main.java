package BaekJoon_7490.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer> list;
    static char[] ops = {'+','-',' '};
    static StringBuilder result = new StringBuilder();
    static List<V> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            list = new ArrayList<>();
            answer = new ArrayList<>();

            int v = Integer.parseInt(br.readLine());
            for(int i=1;i<=v;i++) list.add(i);
            dfs( 0, list.size()-1, new ArrayList<>());
            Collections.sort(answer);
            for(V temp : answer) result.append(temp.str+"\n");
            result.append("\n");
        }

        System.out.println(result);
    }
    public static void dfs(int startDepth, int depth, List<Character> chLists){
        if(startDepth==depth){
            StringBuilder sb = new StringBuilder();

            for(int i=0;i<list.size();i++){
                sb.append(list.get(i));
                if(i<chLists.size()) sb.append(chLists.get(i));
            }

            Stack<String> stack = new Stack<>();
            for(int index=0;index<sb.length();index++){
                if(sb.charAt(index)==' '){
                    String s = stack.pop() + sb.charAt(++index);
                    int v = Integer.parseInt(s);
                    stack.add(String.valueOf(v));
                }else{
                    stack.add(String.valueOf(sb.charAt(index)));
                }
            }

            solution(stack, sb);
            return;
        }

        for(int i=0;i<3;i++){
            chLists.add(ops[i]);
            int size = chLists.size()-1;
            dfs(startDepth+1, depth, chLists);
            chLists.remove(size);
        }
    }

    private static void solution(Stack<String> valueList, StringBuilder sb) {
        Stack<String> stack = new Stack<>();

        for(int i=0;i<valueList.size();i++){
            String s = valueList.get(i);
            if(s.equals("+")){
                int add1 = Integer.parseInt(stack.pop());
                int add2 = Integer.parseInt(valueList.get(++i));
                int sum = add1+add2;
                stack.push(String.valueOf(sum));
            }else if(s.equals("-")){
                int add1 = Integer.parseInt(stack.pop());
                int add2 = Integer.parseInt(valueList.get(++i));
                int sum = add1-add2;
                stack.push(String.valueOf(sum));
            }else{
                stack.push(s);
            }
        }

        if(stack.peek().equals("0")){
            answer.add(new V(sb.toString()));
            //result.append(sb+"\n");
        }
    }
}

class V implements Comparable<V>{
    String str;
    public V(String str){
        this.str=str;
    }

    @Override
    public int compareTo(V o) {
        return this.str.compareTo(o.str);
    }
}