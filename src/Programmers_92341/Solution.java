package Programmers_92341;

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String,String> map = new HashMap<>();
        Map<String,Integer> result = new HashMap<>();
        for(String temp : records){
            String[] str = temp.split(" ");
            String time = str[0];
            String carNumber = str[1];
            String mode = str[2];

            if(mode.equals("IN")){
                map.put(carNumber,time);
            }else{
                String startTime = map.get(carNumber);
                int value = timeDiff(startTime,time);
                if(result.get(carNumber)==null) result.put(carNumber,value);
                else result.put(carNumber,result.get(carNumber)+value);
                map.remove(carNumber);
            }
        }

        for(String key : map.keySet()){
            int value = timeDiff(map.get(key),"23:59");
            if(result.get(key)==null) result.put(key,value);
            else result.put(key,result.get(key)+value);
        }

        Queue<V> queue = new PriorityQueue<>();
        for(String key : result.keySet()){
            int v = 0;
            if(result.get(key)<=fees[0]) v = fees[1];
            else{
                v = fees[1];
                int t = (int)Math.ceil( (double)(result.get(key)-fees[0]) / (double)fees[2] ) * fees[3];
                v+=t;
            }
            queue.add(new V(key,v));
        }

        int[] answer = new int[queue.size()];
        int index = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            answer[index++]=v.v;
        }
        return answer;
    }

    public int timeDiff(String start,String end){
        String[] t1 = start.split(":"); // 05:34
        int time1 = Integer.parseInt(t1[0]); // 05
        int time2 = Integer.parseInt(t1[1]); // 34

        String[] t2 = end.split(":"); // 06:34
        int time3 = Integer.parseInt(t2[0]); // 06
        int time4 = Integer.parseInt(t2[1]); // 34

        int time = time3-time1;
        time*=60;
        time+=time4;
        time-=time2;
        return time;
    }
}

class V implements Comparable<V>{
    String str;
    int v;
    public V(String str, int v){
        this.str=str;
        this.v=v;
    }
    public int compareTo(V o){
        return this.str.compareTo(o.str);
    }
}