package Programmers_17686;

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        Queue<V> queue = new PriorityQueue<>();
        int index = 0;
        for(String str : files){
            String[] t = parsing(str);
            queue.add(new V(str,t[0],t[1],t[2],index++));
        }

        index = 0;
        String[] answer = new String[queue.size()];
        while(!queue.isEmpty()){
            V v = queue.remove();
            answer[index++] = v.name;
        }
        return answer;
    }

    public String[] parsing(String str){
        String[] result = new String[3];

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(int i=0;i<str.length();i++){
            if(index==0 && str.charAt(i)>='0' && str.charAt(i)<='9'){
                result[index++] = sb.toString();
                sb = new StringBuilder();
            }
            if(index==1 && !(str.charAt(i)>='0' && str.charAt(i)<='9')){
                result[index++] = sb.toString();
                sb = new StringBuilder();
            }
            sb.append(str.charAt(i));
        }
        result[index] = sb.toString();

        return result;
    }
}

class V implements Comparable<V>{
    String name;
    String head;
    int number;
    String tail;
    int index;
    public V(String name,String head,String number,String tail,int index){
        this.name=name;
        this.head=head.toUpperCase();
        this.number=Integer.parseInt(number);
        this.tail=tail;
        this.index=index;
    }
    public int compareTo(V o){
        if(this.head.compareTo(o.head)==0 && this.number == o.number) return this.index-o.index;
        if(this.head.compareTo(o.head)==0) return this.number-o.number;
        return this.head.compareTo(o.head);
    }
}