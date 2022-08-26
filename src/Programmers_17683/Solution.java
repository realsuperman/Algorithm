package Programmers_17683;

import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";

        String findPattern = deleteString(m);
        Queue<V> queue = new PriorityQueue<>();
        // C# -> H, D# -> I, F# -> J, G# -> K, A# -> L
        int index = 0;
        for(String temp : musicinfos){
            String[] str = temp.split(",");

            String start = str[0];
            String end = str[1];
            int length = timeDiff(start,end);

            String name = str[2];
            String pattern = str[3];
            String patternMaching = deleteString(extendMusic(pattern,length));

            if(patternMaching.contains(findPattern)){
                queue.add(new V(name,length,index++));
            }

        }
        return queue.size()==0?"(None)":queue.remove().name;
    }

    public String deleteString(String target){
        Stack<String> stack = new Stack<>();

        for(int i=0;i<target.length();i++){
            if(target.charAt(i)=='#'){
                String str = stack.pop();
                if(str.equals("C")){
                    stack.push("H");
                }else if(str.equals("D")){
                    stack.push("I");
                }else if(str.equals("F")){
                    stack.push("J");
                }else if(str.equals("G")){
                    stack.push("K");
                }else if(str.equals("A")){
                    stack.push("L");
                }
            }else{
                stack.push(String.valueOf(target.charAt(i)));
            }
        }
        StringBuilder answer = new StringBuilder();
        while(!stack.isEmpty()){
            answer.append(stack.pop());
        }
        return answer.reverse().toString();
    }

    public int timeDiff(String from,String to){
        String[] t1 = from.split(":");
        int time1 = Integer.parseInt(t1[0]);
        int time2 = Integer.parseInt(t1[1]);

        String[] t2 = to.split(":");
        int time3 = Integer.parseInt(t2[0]);
        int time4 = Integer.parseInt(t2[1]);

        int diff = time3-time1;
        diff*=60;
        diff+=time4;
        diff-=time2;

        return diff;
    }

    public String extendMusic(String music,int length){
        StringBuilder sb = new StringBuilder();

        int index = 0;
        int find = 0;
        while(index<length){
            sb.append(music.charAt(find++));
            if(find>=music.length()) find=0;
            if(music.charAt(find)!='#') index++;
        }
        return sb.toString();
    }
}

class V implements Comparable<V>{
    String name;
    int time;
    int index;
    public V(String name,int time,int index){
        this.name=name;
        this.time=time;
        this.index=index;
    }
    public int compareTo(V o){
        if(this.time==o.time){
            return this.index-o.index;
        }
        return o.time-this.time;
    }
}