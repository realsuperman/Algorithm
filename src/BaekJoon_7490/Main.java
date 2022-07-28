package BaekJoon_7490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] ch = {'+','-',' '};
    static Queue<V> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder printStringBuilder = new StringBuilder();

        for(int i=0;i<N;i++){
            int max = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<max;j++) list.add((j+1));

            List<Character> op = new ArrayList<>();
            result=new PriorityQueue<>();
            dfs(list,op,0);

            while(!result.isEmpty()) printStringBuilder.append(result.remove().str+"\n");
            printStringBuilder.append("\n");
        }
        System.out.println(printStringBuilder);
    }

    public static void dfs(List<Integer> numbers,List<Character> op,int num){
        if(num==numbers.size()-1){
            solution(numbers,op);
            return;
        }

        for(int i=0;i<3;i++){
            op.add(ch[i]);
            int size = op.size();
            dfs(numbers,op,num+1);
            op.remove(size-1);
        }
    }

    public static void solution(List<Integer> numbers,List<Character> op){
        List<String> queue = new ArrayList<>();

        StringBuilder temp = new StringBuilder();
        temp.append(numbers.get(0));
        for(int i=1;i<numbers.size();i++){
            temp.append(op.get(i-1));
            temp.append(numbers.get(i));
        }

        for(int i=0;i<temp.length();i++){
            if(temp.charAt(i)=='+' || temp.charAt(i)=='-') queue.add(String.valueOf(temp.charAt(i)));
            if(temp.charAt(i)>=48 && temp.charAt(i)<58){
                if(i+1>=temp.length()) queue.add(String.valueOf(temp.charAt(i)));
                else{
                    if(temp.charAt(i+1)==' '){
                        String str = String.valueOf(temp.charAt(i));
                        i+=2;
                        while(i<temp.length()){
                            str+=temp.charAt(i);
                            i++;
                            if(i<temp.length()&&temp.charAt(i)==' ') i++;
                            else { i--; break;}
                        }
                        queue.add(str);
                    }else queue.add(String.valueOf(temp.charAt(i)));
                }
            }
        }

        int num = Integer.MAX_VALUE;
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).equals("+")){
                if(num==Integer.MAX_VALUE){
                   num = Integer.parseInt(queue.get(i-1))+Integer.parseInt(queue.get(i+1));
                }else{
                    num = num+Integer.parseInt(queue.get(i+1));
                }
            }else if(queue.get(i).equals("-")){
                if(num==Integer.MAX_VALUE){
                    num = Integer.parseInt(queue.get(i-1))-Integer.parseInt(queue.get(i+1));
                }else{
                    num = num-Integer.parseInt(queue.get(i+1));
                }
            }
        }

        if(num==0) result.add(new V(temp.toString()));
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