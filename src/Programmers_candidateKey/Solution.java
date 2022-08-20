package Programmers_candidateKey;

import java.util.*;
class Solution {
    int row,column,answer;
    boolean[] check;
    Queue<V> queue = new PriorityQueue<>();

    public int solution(String[][] relation) {
        answer = 0;
        row = relation.length;
        column = relation[0].length;

        check = new boolean[column];
        subSet(0);

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(cal(v.check,relation)){
                answer++;
                List<V> list = new ArrayList<>();

                List<Integer> value = new ArrayList<>();
                for(int i=0;i<v.check.length;i++) if(v.check[i]) value.add(i);

                while(!queue.isEmpty()){
                    V temp = queue.remove();
                    boolean check = false;
                    for(int i=0;i<value.size();i++){
                        if(!temp.check[value.get(i)]) {check=true; break;}
                    }

                    if(check) list.add(temp);
                }


                for(V t : list) queue.add(t);
            }
        }
        return answer;
    }

    public boolean cal(boolean[] array,String[][] relation){
        Map<String,Integer> map = new HashMap<>();

        for(int i=0;i<relation.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<array.length;j++) if(array[j]) sb.append(relation[i][j]);
            if(map.get(sb.toString())==null) map.put(sb.toString(),1);
            else map.put(sb.toString(),map.get(sb.toString())+1);
        }
        for(String str : map.keySet()) if(map.get(str)>1) return false;

        return true;
    }

    public void subSet(int cnt){
        if(cnt==column){
            int value = 0;
            for(int i=0;i<column;i++) if(check[i]) value++;
            if(value==0) value=column;
            queue.add(new V(check,value,column));
            return;
        }
        check[cnt]=true;
        subSet(cnt+1);
        check[cnt]=false;
        subSet(cnt+1);
    }

}

class V implements Comparable<V>{
    boolean[] check;
    int cnt;
    public V(boolean[] check,int cnt,int size){
        this.check = new boolean[size];
        for(int i=0;i<check.length;i++) this.check[i]=check[i];
        this.cnt=cnt;
    }

    public int compareTo(V o){
        return this.cnt-o.cnt;
    }
}